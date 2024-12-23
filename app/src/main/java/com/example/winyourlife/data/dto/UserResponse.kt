package com.example.winyourlife.data.dto


import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.dataObjects.UserData
import java.util.Base64

data class UserResponse(val name: String, val email: String, val avatar : String, val streak :Int,
    val longestStreak : Int, val completedTasks:Int , val wonDays: Int, val daysIn: Int, val allTasks : List<TaskResponse>,
    val preparedTasks : List<TaskResponse>, val activeTasks : List<TaskResponse>)
{
    fun toUserData() = UserData(name, email, Base64.getDecoder().decode(avatar), streak, longestStreak, completedTasks, wonDays, daysIn, allTasks.map { toTaskData(it) }.toMutableList(), preparedTasks.map { toTaskData(it) }.toMutableList(), activeTasks.map { toTaskData(it) })

    fun toTaskData(taskResponse: TaskResponse) = TaskData(taskResponse.taskName, Base64.getDecoder().decode(taskResponse.taskImage), taskResponse.isCompleted)
}
