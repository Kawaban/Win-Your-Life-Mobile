package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.FriendRequestCreate
import com.example.winyourlife.data.dto.FriendRequestResponse
import com.example.winyourlife.data.dto.FriendResponse
import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.dto.NotificationResponse
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.data.dto.UserUpdateDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest)

    @GET("api/users")
    suspend fun getUser(): UserResponse

    @PATCH("api/users/data")
    suspend fun updateUserData(@Body userUpdateDataRequest: UserUpdateDataRequest): UserUpdateDataResponse

    @GET("api/notifications")
    suspend fun getNotifications(): List<NotificationResponse>

    @POST("api/friend-request/send")
    suspend fun sendFriendRequest(@Body friendRequestCreate: FriendRequestCreate)

    @POST("api/friend-request/accept")
    suspend fun acceptFriendRequest(@Body friendRequestResponse: FriendRequestResponse)

    @POST("api/friend-request/decline")
    suspend fun declineFriendRequest(@Body friendRequestResponse: FriendRequestResponse)

    @GET("api/users/friends")
    suspend fun getFriends(): List<FriendResponse>
}