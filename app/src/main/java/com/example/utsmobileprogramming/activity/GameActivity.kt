package com.example.utsmobileprogramming.activity

import android.os.Bundle
import com.example.utsmobileprogramming.BaseActivity
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.fragments.CallItEvenFragment
import com.example.utsmobileprogramming.fragments.DivisorFragment
import com.example.utsmobileprogramming.fragments.OperationMathFragment

class GameActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

//        Get Intent
        when (intent.getStringExtra("game")) {
            "divisor" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, DivisorFragment())
                    commit()
                }
            }
            "operationMath" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, OperationMathFragment())
                    commit()
                }
            }
            "callItEven" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, CallItEvenFragment())
                    commit()
                }
            }
        }
    }
}