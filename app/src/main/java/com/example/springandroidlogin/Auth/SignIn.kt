package com.example.springandroidlogin.Auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.springandroidlogin.DataModules.userLogin.UserLoginRequest
import com.example.springandroidlogin.DataModules.userLogin.UserLoginResponse
import com.example.springandroidlogin.MainActivity
import com.example.springandroidlogin.R
import com.example.springandroidlogin.databinding.ActivitySignInBinding
import com.example.springandroidlogin.network.ApiClient

class SignIn : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginUser()
    }

    private fun loginUser() {
        if (checkForSession()){
            TODO("Go to Home")
        }
        binding.signInBtn.setOnClickListener {
            if (binding.email.text.isEmpty()) {
                Toast.makeText(this, "Email Required", Toast.LENGTH_LONG).show()
            } else if (binding.password.text.isEmpty()) {
                Toast.makeText(this, "Password Required", Toast.LENGTH_LONG).show()
            }

            val user =
                UserLoginRequest(binding.email.text.toString(), binding.password.text.toString())

            ApiClient.getInstance(this).loginUser(
                user = user,
                onSuccess = { response ->
                    saveUserSession(response)
                    Toast.makeText(this, "Welcome, ${response.firstName}!", Toast.LENGTH_LONG).show()
                    // Handle successful login (e.g., save token, navigate to next screen)
                },
                onError = { error ->
                    Toast.makeText(this, "Login failed: $error", Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    private fun checkForSession(): Boolean {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        // Retrieve the token from SharedPreferences
        val token = sharedPreferences.getString("token", null)

        Toast.makeText(this, sharedPreferences.getString("firstName",""), Toast.LENGTH_LONG).show()

        // Check if the token exists (indicating an active session)
        return token != null
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun goToSigUpAct(view: View) {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
    /*The function saveUserSession is designed to store user session data in SharedPreferences.
    This allows the app to persist user-related information, such as login details or authentication
    tokens, across app launches.*/
    private fun saveUserSession(response:UserLoginResponse) {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", response.token)
        editor.putInt("userId", response.userId!!)
        editor.putString("username", response.username)
        editor.putString("firstName", response.firstName)
        editor.putString("lastName", response.lastName)
        editor.apply()
    }
}