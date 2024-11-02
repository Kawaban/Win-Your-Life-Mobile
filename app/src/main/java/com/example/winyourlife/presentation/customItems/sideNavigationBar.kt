package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import com.example.winyourlife.presentation.navigation.NavigationScreens

@Composable
fun SideNavigationBar(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(90.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { navController.navigate(NavigationScreens.SETTINGS.name) }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onPrimary)
        }
        IconButton(onClick = { navController.navigate(NavigationScreens.NOTIFICATIONS.name) }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.onPrimary)
        }
        IconButton(onClick = { navController.navigate(NavigationScreens.HOME.name) }) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
