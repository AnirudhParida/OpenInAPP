package com.example.openinapp.data

import android.content.Context
import com.example.openinapp.TokenManager
import com.example.openinapp.data.api.DashboardApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private fun getOkHttpClient(context: Context): OkHttpClient {
        val tokenManager = TokenManager(context)
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val token = tokenManager.getToken()
                val requestBuilder = original.newBuilder()
                token?.let {
                    requestBuilder.header("Authorization", "Bearer $it")
                }
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    fun getRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.inopenapp.com/api/v1/")
            .client(getOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(context: Context): DashboardApiService {
        return getRetrofit(context).create(DashboardApiService::class.java)
    }
}