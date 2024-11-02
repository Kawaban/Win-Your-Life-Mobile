package com.example.winyourlife.data.dto

data class UserResponse(val name: String, val email: String, val avatar : String?, val streak :Int,
    val longestStreak : Int, val completedTasks:Int , val isFriendNotificationActive : Boolean, val isTaskNotificationActive : Boolean)
