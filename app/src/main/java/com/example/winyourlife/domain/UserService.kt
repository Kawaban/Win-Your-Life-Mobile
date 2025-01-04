package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.DeleteFriendRequest
import com.example.winyourlife.data.dto.FriendResponse
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.data.network.performNetworkOperation
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val apiService: ApiService, private val currentUser: CurrentUser, private val jwtManager: JwtManager){
    suspend fun getUser(): Resource<UserResponse> {
        return performNetworkOperation {
            val result = apiService.getUser()
            currentUser.userData = result.toUserData()
            return@performNetworkOperation result
        }
    }

    suspend fun updateUserData(userUpdateDataRequest: UserUpdateDataRequest): Resource<Unit> {
        return performNetworkOperation {
            val result = apiService.updateUserData(userUpdateDataRequest)
            if(result.token != null) {
                jwtManager.setJwt(result.token)
            }
        }
    }

    suspend fun getFriends(): Resource<List<FriendResponse>> {
        return performNetworkOperation {
            apiService.getFriends()
        }
    }

    suspend fun deleteFriend(email : String): Resource<Unit> {
        return performNetworkOperation {
            apiService.deleteFriend(email)
        }
    }



}