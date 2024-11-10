package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.FriendRequestCreate
import com.example.winyourlife.data.dto.FriendRequestResponse
import com.example.winyourlife.data.dto.NotificationResponse
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.performNetworkOperation
import com.example.winyourlife.domain.wrapper.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationService @Inject constructor(private val apiService: ApiService) {
    suspend fun getNotifications(): Resource<List<NotificationResponse>>{
        return performNetworkOperation {
            apiService.getNotifications()
        }
    }

    suspend fun sendFriendRequest(friendRequestCreate: FriendRequestCreate): Resource<Unit>{
        return performNetworkOperation {
            apiService.sendFriendRequest(friendRequestCreate)
        }
    }

    suspend fun acceptFriendRequest(friendRequestResponse: FriendRequestResponse): Resource<Unit>{
        return performNetworkOperation {
            apiService.acceptFriendRequest(friendRequestResponse)
        }
    }

    suspend fun declineFriendRequest(friendRequestResponse: FriendRequestResponse): Resource<Unit>{
        return performNetworkOperation {
            apiService.declineFriendRequest(friendRequestResponse)
        }
    }

}