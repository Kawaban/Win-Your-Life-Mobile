package com.example.winyourlife.data

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("user/")
    suspend fun getUser(@Header("Authorization") token:String): UserResponse
}