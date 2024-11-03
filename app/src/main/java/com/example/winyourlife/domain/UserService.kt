package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.domain.dto.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val apiService: ApiService, private val currentUser: CurrentUser, private val jwtManager: JwtManager){
    suspend fun getUser(): Resource<UserResponse> {
        return try {
            val result = apiService.getUser()
            currentUser.userData = result.toUserData()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.javaClass.name, null)
        }
    }

    suspend fun updateUserData(userUpdateDataRequest: UserUpdateDataRequest): Resource<Nothing> {
        return try {
            val result = apiService.updateUserData(userUpdateDataRequest)
            if(result.token != null) {
                jwtManager.setJwt(result.token)
            }
            else{
                println("Token is null")
            }
            Resource.Success(null)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.javaClass.name, null)
        }
    }
}