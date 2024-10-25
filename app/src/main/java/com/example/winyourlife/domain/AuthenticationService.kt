package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.ApiService
import com.example.winyourlife.data.JwtManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager) {

    suspend fun login(loginRequest: LoginRequest): Resource<Any>{
        return try{
            val result = apiService.login(loginRequest)
            jwtManager.setJwt(result.token)
            Resource.Success(null)
        }
        catch (e: Exception) {
            Resource.Error(e.javaClass.name, null)
        }

    }
}