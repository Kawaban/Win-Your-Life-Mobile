package com.example.winyourlife.presentation.notificationspage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import com.example.winyourlife.presentation.dataObjects.NotificationData

@Composable
fun NotificationCard(
    time: String,
    message: String,
    hasActions: Boolean,
    onClose: () -> Unit,
    onAccept: (() -> Unit)? = null,
    onReject: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFFFA500)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF1C1C1E))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close notification",
                        tint = Color(0xFFFFA500)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (hasActions) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onAccept?.invoke() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA500)
                        ),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Accept", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onReject?.invoke() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A)),
                        border = BorderStroke(1.dp, Color(0xFFFFA500)),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Reject", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationList(notifications: List<NotificationData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(max = 570.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notifications) { notification ->
            NotificationCard(
                time = notification.time,
                message = notification.message,
                hasActions = notification.hasActions,
                onClose = notification.onClose,
                onAccept = notification.onAccept,
                onReject = notification.onReject
            )
        }
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController, viewModel: NotificationsViewModel = hiltViewModel()) {

    val notifications = listOf(
        NotificationData(
            time = "9:41 AM",
            message = "Kasia invites you to be friends!",
            hasActions = true,
            onClose = { },
            onAccept = { },
            onReject = { }
        ),
        NotificationData(
            time = "9:41 AM",
            message = "Don't forget to plan your tasks for tomorrow!",
            hasActions = false,
            onClose = { }
        ),
        NotificationData(
            time = "9:41 AM",
            message = "Marcin accepted your friend request;ns;gbassodgb[asodbgi[sdbg[oasbdg[oasbdg[osbdgbaskldgb[aodgb;kjasdb g[as!",
            hasActions = false,
            onClose = { }
        ),
        NotificationData(
            time = "9:41 AM",
            message = "Marcin accepted your friend request!",
            hasActions = false,
            onClose = { }
        ),
        NotificationData(
            time = "9:41 AM",
            message = "Marcin accepted your friend request!",
            hasActions = false,
            onClose = { }
        ),
        NotificationData(
            time = "9:41 AM",
            message = "Marcin accepted your friend request!",
            hasActions = false,
            onClose = { }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("NOTIFICATIONS")

        Spacer(modifier = Modifier.height(40.dp))

        NotificationList(notifications = notifications)

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
