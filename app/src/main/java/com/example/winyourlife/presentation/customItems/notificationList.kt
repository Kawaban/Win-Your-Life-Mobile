package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.dataObjects.NotificationData
import com.example.winyourlife.presentation.notificationspage.NotificationCard

@Composable
fun NotificationList(notifications: List<NotificationData>) {
    LazyColumn(
        modifier = Modifier
            .width(316.dp)
            .padding(16.dp)
            .heightIn(max = 550.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notifications) { notification ->
            NotificationCard(
                time = notification.time,
                message = notification.message,
                hasActions = notification.hasActions,
                onClose = notification.onClose,
                onAccept = notification.onAccept,
                onReject = notification.onReject,
                id = notification.id
            )
        }
    }
}