package com.example.springandroidlogin.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.springandroidlogin.R
import com.example.springandroidlogin.databinding.ActivityHomeBinding

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
            val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("token")
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}