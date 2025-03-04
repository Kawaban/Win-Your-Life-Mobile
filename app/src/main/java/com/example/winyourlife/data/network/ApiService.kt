package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.CreateTaskRequest
import com.example.winyourlife.data.dto.DeleteFriendRequest
import com.example.winyourlife.data.dto.FriendRequestCreate
import com.example.winyourlife.data.dto.FriendRequestResponse
import com.example.winyourlife.data.dto.FriendResponse
import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.data.dto.LoginResponse
import com.example.winyourlife.data.dto.NotificationResponse
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.data.dto.TaskCompletion
import com.example.winyourlife.data.dto.TaskPreparation
import com.example.winyourlife.data.dto.TaskResponse
import com.example.winyourlife.data.dto.TaskUpdate
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.data.dto.UserUpdateDataResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET("api/tasks")
    suspend fun getAllTasks(): List<TaskResponse>

    @GET("api/tasks/active")
    suspend fun getActiveTasks(): List<TaskResponse>

    @GET("api/tasks/preparation")
    suspend fun getPreparedTasks(): List<TaskResponse>

    @POST("api/tasks")
    suspend fun createTask(@Body createTaskRequest: CreateTaskRequest)

    @PUT("api/tasks")
    suspend fun updateTask(@Body taskUpdate: TaskUpdate)

    @DELETE("api/tasks/{taskName}")
    suspend fun deleteTask(@Path("taskName") taskDelete: String)

    @PATCH("api/tasks/completion")
    suspend fun completeTask(@Body taskCompletion: List<TaskCompletion>)

    @PATCH("api/tasks/preparation")
    suspend fun prepareTask(@Body taskPreparation: TaskPreparation)

    @DELETE("api/users/friends/{email}")
    suspend fun deleteFriend(@Path("email") email: String)

    @POST("api/auth/remind-password/{email}")
    suspend fun remindPassword(@Path("email") email: String)





}