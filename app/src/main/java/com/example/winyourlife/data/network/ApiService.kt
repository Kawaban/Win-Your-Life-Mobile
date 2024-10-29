package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("register/")
    suspend fun register(@Body registerRequest: RegisterRequest): Any

    @GET("user/")
    suspend fun getUser(): UserResponse
}