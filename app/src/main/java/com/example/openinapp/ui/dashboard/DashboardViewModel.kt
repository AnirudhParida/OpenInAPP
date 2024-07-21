package com.example.openinapp.ui.dashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp.data.RetrofitInstance
import com.example.openinapp.data.local.TokenManager
import com.example.openinapp.data.model.DashboardResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val apiService = RetrofitInstance.getApiService(application)
    private val dataManager = TokenManager(application)

    private val _dashboardResponse = MutableStateFlow<DashboardResponse?>(dataManager.getDashboardData())
    val dashboardResponse: StateFlow<DashboardResponse?> = _dashboardResponse

    init {
        if (_dashboardResponse.value == null) {
            fetchDashboardData()
        }
    }

    private fun fetchDashboardData() {
        viewModelScope.launch {
            try {
                val response = apiService.getDashboardData()
                _dashboardResponse.value = response
                dataManager.saveDashboardData(response)
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Error fetching data", e)
            }
        }
    }
}