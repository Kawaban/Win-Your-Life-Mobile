package com.example.winyourlife.presentation.notificationspage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.NotificationList
import com.example.winyourlife.presentation.customItems.SideNavigationBar
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
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .height(130.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_notification_description),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
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
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(stringResource(id = R.string.accept_button), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyLarge)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onReject?.invoke() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(stringResource(id = R.string.reject_button), color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationsPage(navController: NavHostController) {
    ResponsiveLayout(navController)
}

@Composable
fun ResponsiveLayout(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }
}

@Composable
fun LandscapeLayout(navController: NavHostController, viewModel: NotificationsViewModel = hiltViewModel()) {

    val notifications = listOf(
        NotificationData(
            time = "9:41 AM",
            message = "Greg invites you to be friends!",
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

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            NotificationList(notifications = notifications)

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: NotificationsViewModel = hiltViewModel()) {

    val notifications = listOf(
        NotificationData(
            time = "9:41 AM",
            message = "Greg invites you to be friends!",
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
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.notifications_hd))

        Spacer(modifier = Modifier.height(40.dp))

        NotificationList(notifications = notifications)

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
