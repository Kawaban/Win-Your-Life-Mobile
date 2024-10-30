package com.example.winyourlife.presentation.util

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.navigation.NavigationScreens

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFFFFA500),
        contentColor = Color.White,
        modifier = Modifier
            .height(90.dp)
    ) {
        Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate(NavigationScreens.HOME.name) }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate(NavigationScreens.NOTIFICATIONS.name) }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { navController.navigate(NavigationScreens.SETTINGS.name) }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    }
}