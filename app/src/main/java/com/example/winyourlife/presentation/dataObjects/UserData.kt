package com.example.winyourlife.presentation.dataObjects

data class UserData(
    val nickname: String,
    val email: String,
    val avatar: Int,
    val streak: Int,
    val longestStreak: Int,
    val completedTasks: Int,
    val numberOfDaysWon: Int,
    val accountSince: Int,
    val isFriendNotificationActive: Boolean,
    val isDailyReminderActive: Boolean
)