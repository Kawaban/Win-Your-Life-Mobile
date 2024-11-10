package com.example.winyourlife.presentation.dataObjects

import java.util.UUID

data class NotificationData(
    val id: UUID,
    val time: String,
    val message: String,
    val hasActions: Boolean,
    val onClose: (Any?) -> Unit,
    val onAccept: ((Any?) -> Unit)? = null,
    val onReject: ((Any?) -> Unit)? = null
)