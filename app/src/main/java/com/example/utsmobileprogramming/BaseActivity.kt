package com.example.utsmobileprogramming

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle double klik back
        var backPressedOnce = false
        val handler = Handler(Looper.getMainLooper())
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Perform the necessary actions to handle the back button press
                if (backPressedOnce) {
                    // Exit the app
                    finish()
                    return
                }
                backPressedOnce = true
                Toast.makeText(this@BaseActivity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
                handler.postDelayed({ backPressedOnce = false }, 2000)
            }
        }
        onBackPressedDispatcher.addCallback(this,callback)
        //Hide Status Bar
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this,R.color.red)
    }
}
