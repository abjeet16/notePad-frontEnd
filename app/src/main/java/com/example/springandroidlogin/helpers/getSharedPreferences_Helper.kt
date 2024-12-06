package com.example.springandroidlogin.helpers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.springandroidlogin.DataModules.userLogin.UserLoginResponse
import com.example.springandroidlogin.activitys.MainActivity

class SharedPreferencesHelper(private val context: Context) {
    fun saveUserSession(response: UserLoginResponse) {
        val sharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save user details
        editor.putString("token", response.token)
        editor.putInt("userId", response.userId ?: 0) // Default value for userId
        editor.putString("username", response.username)
        editor.putString("firstName", response.firstName ?: "") // Default to empty string
        editor.putString("lastName", response.lastName ?: "")   // Default to empty string

        // Apply changes
        editor.apply()
    }
    fun deleteUserSession() {
        val sharedPreferences = context.getSharedPreferences("UserSession",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.remove("userId")
        editor.remove("username")
        editor.remove("firstName")
        editor.remove("lastName")
        editor.apply()
    }
}
