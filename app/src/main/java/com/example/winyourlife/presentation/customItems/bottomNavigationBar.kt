package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.navigation.NavigationScreens
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.AccountCircle
import com.example.winyourlife.R
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface

@Composable
fun BottomNavigationBar(navController: NavController, viewModel: ViewModelCustomInterface) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .height(90.dp)
    ) {
        Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.HOME.name) }) {
                Icon(Icons.Default.Home, contentDescription = stringResource(id = R.string.home_description))
            }
            IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.NOTIFICATIONS.name) }) {
                Icon(Icons.Default.Notifications, contentDescription = stringResource(id = R.string.notifications_description))
            }
            IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.PROFILE.name) }) {
                Icon(Icons.Default.AccountCircle, contentDescription = stringResource(id = R.string.profile_description))
            }
            IconButton(onClick = {viewModel.resetViewModel(); navController.navigate(NavigationScreens.SETTINGS.name) }) {
                Icon(Icons.Default.Settings, contentDescription = stringResource(id = R.string.settings_description))
            }
        }
    }
}
