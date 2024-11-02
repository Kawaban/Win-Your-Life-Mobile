package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.data.exceptions.BadCredentialsException
import com.example.winyourlife.domain.dto.Resource
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager) {
    private suspend fun <T> performNetworkOperation(
        operation: suspend () -> T
    ): Resource<Nothing> {
        return try {
            operation()
            Resource.Success(null)
        } catch (e: SocketTimeoutException) {
            Resource.Error("Server is not available", null)
        } catch (e: BadCredentialsException) {
            Resource.Error(e.message ?: "Internal Server Error", null)
        } catch (e: IOException) {
            Resource.Error("Network error", null)
        } catch (e: Exception) {
            println(e.printStackTrace())
            Resource.Error(e.javaClass.name, null)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Resource<Nothing> {
        return performNetworkOperation {
            val result = apiService.login(loginRequest)
            jwtManager.setJwt(result.token)
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Resource<Nothing> {
        return performNetworkOperation {
            apiService.register(registerRequest)
        }
    }
}