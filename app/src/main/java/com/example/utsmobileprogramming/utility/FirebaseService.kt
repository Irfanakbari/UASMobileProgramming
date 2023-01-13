package com.example.utsmobileprogramming.utility

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import java.util.*


class FirebaseService {

    companion object {
        fun saveSkor(skor: Int?, type: String?) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val db = Firebase.firestore
            if (currentUser != null) {
                val username = currentUser.displayName
                val docRef = db.collection(type.toString()).document(username!!)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.data != null) {
                        val oldskor = documentSnapshot.getField<Int>("skor")
                        if (skor != null) {
                            if (oldskor.toString().toInt() < skor) {
                                docRef.update(hashMapOf(
                                    "skor" to skor,
                                    "time" to FieldValue.serverTimestamp()
                                ))
                            }
                        }
                    } else {
                        docRef.set(hashMapOf(
                            "skor" to skor,
                            "time" to FieldValue.serverTimestamp()
                        ), SetOptions.merge())
                    }
                }
            }
        }
        @OptIn(DelicateCoroutinesApi::class)
        fun getAllData(): Deferred<QuerySnapshot> = GlobalScope.async {
            val db = FirebaseFirestore.getInstance()
            val ref = db.collection("Divisor").limit(10)
            ref.orderBy("skor", Query.Direction.DESCENDING).get().await()
        }
    }


}
