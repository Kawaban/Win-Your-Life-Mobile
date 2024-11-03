package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.domain.dto.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val apiService: ApiService, private val currentUser: CurrentUser){
    suspend fun getUser(): Resource<UserResponse> {
        return try {
            val result = apiService.getUser()
            currentUser.userData = result
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.javaClass.name, null)
        }
    }

    suspend fun updateUserData(userUpdateDataRequest: UserUpdateDataRequest): Resource<Nothing> {
        return try {
            apiService.updateUserData(userUpdateDataRequest)
            Resource.Success(null)
        } catch (e: Exception) {
            Resource.Error(e.javaClass.name, null)
        }
    }
}