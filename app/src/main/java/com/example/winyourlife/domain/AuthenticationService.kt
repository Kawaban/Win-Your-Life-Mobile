package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.ApiService
import com.example.winyourlife.data.JwtManager
import com.example.winyourlife.data.exceptions.BadCredentialsException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager) {

    suspend fun login(loginRequest: LoginRequest): Resource<Nothing>{
        return try{
            val result = apiService.login(loginRequest)
            jwtManager.setJwt(result.access)
            Resource.Success(null)
        }
        catch (e: SocketTimeoutException){
            Resource.Error("Server is not available", null)
        }
        catch (e: BadCredentialsException){
            Resource.Error(e.message ?: "Internal Server Error", null)
        }
        catch (e: IOException){
            Resource.Error("Network error", null)
        }
        catch (e: Exception) {
            println(e.printStackTrace())
            Resource.Error(e.javaClass.name, null)
        }

    }
}