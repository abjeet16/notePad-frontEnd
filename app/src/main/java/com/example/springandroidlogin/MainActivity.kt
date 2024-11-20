package com.example.springandroidlogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.springandroidlogin.Auth.SignIn
import com.example.springandroidlogin.Auth.SignUp
import com.example.springandroidlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set up click listeners for navigation buttons
        binding.signIn.setOnClickListener {
            navigateToSignIn()
        }

        binding.signUp.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun navigateToSignIn() {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}

