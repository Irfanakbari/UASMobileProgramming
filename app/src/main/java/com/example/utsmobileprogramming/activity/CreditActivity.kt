package com.example.utsmobileprogramming.activity

import android.os.Bundle
import android.widget.TextView
import com.example.utsmobileprogramming.BaseActivity
import com.example.utsmobileprogramming.R

class CreditActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)
        val backbutton = findViewById<TextView>(R.id.backmenu)

        backbutton.setOnClickListener{
            finish()
        }
    }
}