package com.example.utsmobileprogramming.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utsmobileprogramming.BaseActivity
import com.example.utsmobileprogramming.R
import com.google.firebase.auth.FirebaseAuth


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
        val signOut = findViewById<TextView>(R.id.signout)

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
            startActivity(Intent(this, CreditActivity::class.java))
        }
        signOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            recreate()
        }
    }
    override fun onStart() {
        super.onStart()
        if (auth == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}