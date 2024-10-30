package com.example.winyourlife.presentation.notificationspage

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
import com.example.winyourlife.presentation.util.BottomNavigationBar
import com.example.winyourlife.presentation.util.Headline

@Composable
fun NotificationsScreen(navController: NavHostController, viewModel: NotificationsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("NOTIFICATIONS")

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
