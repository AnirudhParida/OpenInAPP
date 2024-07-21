package com.example.openinapp.data.api

import retrofit2.http.GET
import com.example.openinapp.data.model.DashboardResponse

interface DashboardApiService {
    @GET("dashboardNew")
    suspend fun getDashboardData(): DashboardResponse
}