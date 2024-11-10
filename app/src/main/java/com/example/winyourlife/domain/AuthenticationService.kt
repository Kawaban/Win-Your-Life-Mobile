package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.data.network.performNetworkOperation
import com.example.winyourlife.domain.wrapper.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager) {

    suspend fun login(loginRequest: LoginRequest): Resource<Unit> {
        return performNetworkOperation {
            val result = apiService.login(loginRequest)
            jwtManager.setJwt(result.token)
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Resource<Unit> {
        return performNetworkOperation {
            apiService.register(registerRequest)
        }
    }
}