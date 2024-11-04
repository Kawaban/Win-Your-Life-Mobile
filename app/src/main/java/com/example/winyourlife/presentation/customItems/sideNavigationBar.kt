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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.res.stringResource
import com.example.winyourlife.R
import com.example.winyourlife.presentation.ViewModelCustomInterface
import com.example.winyourlife.presentation.navigation.NavigationScreens

@Composable
fun SideNavigationBar(navController: NavController, viewModel: ViewModelCustomInterface) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(90.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.SETTINGS.name) }) {
            Icon(Icons.Default.Settings, contentDescription = stringResource(id = R.string.settings_description), tint = MaterialTheme.colorScheme.onPrimary)
        }
        IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.PROFILE.name) }) {
            Icon(Icons.Default.AccountCircle, contentDescription = stringResource(id = R.string.profile_description))
        }
        IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.NOTIFICATIONS.name) }) {
            Icon(Icons.Default.Notifications, contentDescription = stringResource(id = R.string.notifications_description), tint = MaterialTheme.colorScheme.onPrimary)
        }
        IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.HOME.name) }) {
            Icon(Icons.Default.Home, contentDescription = stringResource(id = R.string.home_description), tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
