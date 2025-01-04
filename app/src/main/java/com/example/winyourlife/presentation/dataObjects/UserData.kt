package com.example.winyourlife.presentation.dataObjects

data class UserData(
    var name: String,
    var email: String,
    var avatar: ByteArray,
    var streak: Int,
    var longestStreak: Int,
    var completedTasks: Int,
    val wonDays: Int,
    val daysIn: Int,
    val allTasks: MutableList<TaskData>,
    val preparedTasks: MutableList<TaskData>,
    val activeTasks: MutableList<TaskData>
)