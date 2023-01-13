package com.example.utsmobileprogramming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utsmobileprogramming.utility.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random


class HomeActivity : BaseActivity() {
    private var auth = FirebaseAuth.getInstance().currentUser
    private var username = auth?.displayName.toString().split(" ")[0]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inisialisasi tombol
        val divisorButton = findViewById<Button>(R.id.divisor)
        val operationMathButton = findViewById<Button>(R.id.operationMath)
        val callItEvenButton = findViewById<Button>(R.id.callItEven)
        val credit = findViewById<Button>(R.id.credit)
        val usernameText = findViewById<TextView>(R.id.userLogin)

        usernameText.text = username


        // Button Click Listener
        val intent = Intent(this, GameActivity::class.java)
        divisorButton.setOnClickListener {
            intent.putExtra("game", "divisor")
            startActivity(intent)
        }
        operationMathButton.setOnClickListener {
            intent.putExtra("game", "operationMath")
            startActivity(intent)
        }
        callItEvenButton.setOnClickListener {
            intent.putExtra("game", "callItEven")
            startActivity(intent)
        }
        credit.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            recreate()
            val r = Random.nextInt(1,100)
            FirebaseService.saveSkor(r,"Divisor")


            // To show the dialog fragment
//            val fragment = UsernameModal()
//            fragment.show(supportFragmentManager, "username_dial")
        }
    }
    override fun onStart() {
        super.onStart()
        if (auth == null){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}