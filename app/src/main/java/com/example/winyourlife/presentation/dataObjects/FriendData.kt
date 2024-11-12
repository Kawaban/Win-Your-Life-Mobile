package com.example.winyourlife.presentation.dataObjects

data class FriendData(
    val avatar: ByteArray,
    val nickname: String,
    val isBetter: Boolean,
    val period: Int,
    val id: Int
)