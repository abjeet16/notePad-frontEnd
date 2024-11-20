package com.example.springandroidlogin.Auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.springandroidlogin.MainActivity
import com.example.springandroidlogin.R
import com.example.springandroidlogin.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun goToSigUpAct(view: View) {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}