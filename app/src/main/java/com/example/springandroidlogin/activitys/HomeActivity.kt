package com.example.springandroidlogin.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.springandroidlogin.R
import com.example.springandroidlogin.databinding.ActivityHomeBinding
import com.example.springandroidlogin.helpers.SharedPreferencesHelper

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        logout()
    }

    private fun logout() {
        binding.logout.setOnClickListener {
            val helper = SharedPreferencesHelper(this) // 'this' is an Activity or Context
            helper.deleteUserSession()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}