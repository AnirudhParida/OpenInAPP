package com.example.openinapp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.openinapp.data.model.DashboardResponse
import com.example.openinapp.data.model.defaultDashboardResponse
import com.google.gson.Gson

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val TOKEN_KEY = "token_key"
        private const val DATA_KEY = "dashboard_data"
    }

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun saveDashboardData(data: DashboardResponse) {
        val json = gson.toJson(data)
        prefs.edit().putString(DATA_KEY, json).apply()
    }

    fun getDashboardData(): DashboardResponse? {
        val json = prefs.getString(DATA_KEY, null)
        return if (json != null) {
            gson.fromJson(json, DashboardResponse::class.java)
        } else {
            defaultDashboardResponse  // Return default values
        }
    }
}