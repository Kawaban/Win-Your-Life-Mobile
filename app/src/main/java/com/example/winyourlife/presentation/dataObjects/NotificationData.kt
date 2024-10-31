package com.example.winyourlife.presentation.dataObjects

data class NotificationData(
    val time: String,
    val message: String,
    val hasActions: Boolean,
    val onClose: () -> Unit,
    val onAccept: (() -> Unit)? = null,
    val onReject: (() -> Unit)? = null
)