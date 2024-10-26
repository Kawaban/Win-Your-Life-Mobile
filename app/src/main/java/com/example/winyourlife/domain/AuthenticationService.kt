package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.ApiService
import com.example.winyourlife.data.JwtManager
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val apiService: ApiService, private val jwtManager: JwtManager) {

    suspend fun login(loginRequest: LoginRequest): Resource<Any>{
        return try{
            val result = apiService.login(loginRequest)
            jwtManager.setJwt(result.access)
            Resource.Success(null)
        }
        catch (e: HttpException){
            Resource.Error(e.message(), null)
        }
        catch (e: Exception) {
            println(e.printStackTrace())
            Resource.Error(e.javaClass.name, null)
        }

    }
}