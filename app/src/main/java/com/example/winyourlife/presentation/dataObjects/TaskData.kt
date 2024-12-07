package com.example.winyourlife.presentation.dataObjects

data class TaskData(
    val label: String,
    var image: ByteArray,
    var isCompleted: Boolean
)