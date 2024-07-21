package com.example.openinapp.data.repository

import com.example.openinapp.data.api.DashboardApiService
import com.example.openinapp.data.local.TokenManager
import com.example.openinapp.data.model.DashboardResponse
import com.example.openinapp.data.model.defaultDashboardResponse

class DashboardRepository(private val apiService: DashboardApiService, private val tokenManager: TokenManager) {
    suspend fun getDashboardData(): DashboardResponse {
        return try {
            val response = apiService.getDashboardData()
            tokenManager.saveDashboardData(response)
            response
        } catch (e: Exception) {
            tokenManager.getDashboardData() ?: defaultDashboardResponse
        }
    }
}