package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.ApiService
import com.example.winyourlife.data.JwtManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager){
    suspend fun getUser(): Resource<UserResponse> {
        return try {
            val result = apiService.getUser(jwtManager.getJwt().orEmpty())
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.javaClass.name, null)
        }
    }
}