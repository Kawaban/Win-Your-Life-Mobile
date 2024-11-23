package com.example.winyourlife.data.dto


import com.example.winyourlife.presentation.dataObjects.UserData
import java.util.Base64

data class UserResponse(val name: String, val email: String, val avatar : String, val streak :Int,
    val longestStreak : Int, val completedTasks:Int , val wonDays: Int, val daysIn: Int)
{
    fun toUserData() = UserData(name, email, Base64.getDecoder().decode(avatar), streak, longestStreak, completedTasks, wonDays, daysIn)
}
