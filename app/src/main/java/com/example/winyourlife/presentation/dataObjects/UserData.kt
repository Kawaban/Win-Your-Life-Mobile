package com.example.winyourlife.presentation.dataObjects

data class UserData(
    var name: String,
    var email: String,
    var avatar: ByteArray,
    var streak: Int,
    var longestStreak: Int,
    var completedTasks: Int,
//    val numberOfDaysWon: Int,
//    val accountSince: Int,
    var isFriendNotificationActive: Boolean,
    var isDailyReminderActive: Boolean
)