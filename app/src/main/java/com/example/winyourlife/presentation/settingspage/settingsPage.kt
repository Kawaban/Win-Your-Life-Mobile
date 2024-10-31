package com.example.winyourlife.presentation.settingspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton

@Composable
fun SettingsScreen(navController: NavHostController, viewModel: SettingsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("SETTINGS")

        Spacer(modifier = Modifier.height(40.dp))

        MyHorizontalDivider()

        Text(
            text = "NOTIFICATIONS OPTIONS",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 60.dp),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Friend's notifications", fontSize = 20.sp, color = Color.White)
            Switch(
                checked = true,
                onCheckedChange = {},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFFFA500),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }

        Row(
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Daily reminders", fontSize = 20.sp, color = Color.White)
            Switch(
                checked = false,
                onCheckedChange = {},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFFFA500),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        Spacer(modifier = Modifier.height(20.dp))

        OrangeButton({ navController.navigate(NavigationScreens.PROFILE.name) },"Your profile")

        OrangeButton({ navController.navigate(NavigationScreens.STATISTICS.name) }, "Statistics")

        OrangeButton({ navController.navigate(NavigationScreens.LOGIN.name) }, "Log out")

        Spacer(modifier = Modifier.height(30.dp))

        BottomNavigationBar(navController)
    }
}
