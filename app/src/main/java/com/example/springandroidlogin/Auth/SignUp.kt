package com.example.springandroidlogin.Auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.springandroidlogin.MainActivity
import com.example.springandroidlogin.R
import com.example.springandroidlogin.databinding.ActivitySignInBinding
import com.example.springandroidlogin.databinding.ActivitySignUpBinding
import com.example.springandroidlogin.helpers.StringHelper

class SignUp : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            processFormFields()
        }
    }
    fun goToSigInAct(view: View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
    fun goToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun processFormFields(){
        if (!validateAllEditText()){
            return
        }

        var requestQueue:RequestQueue = Volley.newRequestQueue(this)
        //url for spring boot backend
        val url:String =  "http://192.168.29.30:8080/api/user/register"

        // Create a StringRequest object
        val stringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                if (response.equals("success", ignoreCase = true)) {
                    // Clear all input fields
                    binding.firstName.text = null
                    binding.lastName.text = null
                    binding.email.text = null
                    binding.password.text = null
                    binding.confirm.text = null

                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                println(error.message)
                Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["first_name"] = binding.firstName.text.toString()
                params["last_name"] = binding.lastName.text.toString()
                params["email"] = binding.email.text.toString()
                params["password"] = binding.password.text.toString()
                return params
            }
        }

// Add the request to the RequestQueue
        requestQueue.add(stringRequest)
        Toast.makeText(this, "Form Submitted", Toast.LENGTH_LONG).show()
    }
    fun validateAllEditText():Boolean{
        var firstName = binding.firstName.text.toString().trim()
        var lastName = binding.lastName.text.toString().trim()
        var email = binding.email.text.toString().trim()
        var password = binding.password.text.toString().trim()
        var confirmPassword = binding.confirm.text.toString().trim()

        if (firstName.isEmpty()){
            binding.firstName.error = "First Name Required"
            return false
        }
        if (lastName.isEmpty()){
            binding.lastName.error = "Last Name Required"
            return false
        }

        if (email.isEmpty()){
            binding.email.error = "Email Required"
            return false
        }else if(!StringHelper().regexEmailValidationPattern(email)){
            binding.email.error = "Invalid Email"
            return false
        }

        if (password.isEmpty()) {
            binding.password.error = "Password Required"
            return false
        } else {
            val error = StringHelper().validatePassword(password)
            if (error != null) {
                binding.password.error = error // Show the specific error
                return false
            }
        }

        if (confirmPassword.isEmpty()) {
            binding.confirm.error = "Confirm Password Required"
            return false
        }else if(password != confirmPassword) {
            binding.confirm.error = "Password Not Match"
            return false
        }

        binding.firstName.error = null
        binding.lastName.error = null
        binding.email.error = null
        binding.password.error = null
        binding.confirm.error = null

        return true
    }
}