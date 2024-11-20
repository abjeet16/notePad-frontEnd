package com.example.springandroidlogin.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.springandroidlogin.DataModules.UserRegister

class ApiClient private constructor(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)
    private val defaultUrl: String = "http://192.168.29.30:8080/api"

    companion object {
        @Volatile
        private var INSTANCE: ApiClient? = null

        fun getInstance(context: Context): ApiClient {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiClient(context).also { INSTANCE = it }
            }
        }
    }

    fun registerUser(
        user: UserRegister,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "/user/register"
        // Ensuring the URL is correctly formatted
        val fullUrl = if (defaultUrl.endsWith("/")) "$defaultUrl$url" else "$defaultUrl/$url"

        val stringRequest = object : StringRequest(Method.POST, fullUrl,
            { response ->
                // If successful, invoke the onSuccess callback with the response message
                onSuccess(response)
            },
            { error ->
                Log.e("ApiClient", "Error during registration: ${error.message}")
                // Handle error if network or server issues occur
                onError(error.message ?: "An error occurred")
            }
        ) {
            override fun getParams(): Map<String, String> {
                return mapOf(
                    "first_name" to user.firstName,
                    "last_name" to user.lastName,
                    "email" to user.email,
                    "password" to user.password
                )
            }
        }

        requestQueue.add(stringRequest)
    }
}
