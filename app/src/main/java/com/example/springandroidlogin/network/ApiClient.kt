package com.example.springandroidlogin.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.springandroidlogin.DataModules.UserRegister

class ApiClient private constructor(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

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
        url: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val stringRequest = object : StringRequest(Method.POST, url,
            { response ->
                onSuccess(response)
            },
            { error ->
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
