package com.example.utsmobileprogramming.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.utsmobileprogramming.BaseActivity
import com.example.utsmobileprogramming.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity()  {
    private lateinit var auth: FirebaseAuth

    // dipanggil saat hasil masuk ke aplikasi menggunakan Firebase Auth UI. Saat hasil masuk kembali, maka akan memanggil fungsi onSignInResult.
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        Listen Sign in button
        val buttonGoogle = findViewById<SignInButton>(R.id.sign_in_button)
        buttonGoogle.setOnClickListener {
            createSignInIntent()
        }

        // User is already signed in, proceed to the main activity
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun createSignInIntent() {
        // FirebaseUI AUTH
        // Metode login yang dipakai
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())
        // Create and launch sign-in intent
        signInLauncher.launch(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(false)
            .setAvailableProviders(providers)
            .build())
    }

    // Dipanggil Ketika Proses Login mengembalikan sesuatu 'return'
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            // Pindah ke MainActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Sign in gagal
            if (response == null) {
                // User cancelled the sign-in flow
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                return
            }
            if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                // No network connection
                Toast.makeText(this, "This is error", Toast.LENGTH_SHORT).show()
                return
            }
            Toast.makeText(this, "This is error anonim", Toast.LENGTH_SHORT).show()
        }
    }
}