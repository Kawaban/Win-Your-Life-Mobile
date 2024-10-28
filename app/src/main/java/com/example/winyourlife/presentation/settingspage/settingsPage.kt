package com.example.winyourlife.presentation.settingspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Settings",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Notifications",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 60.dp),
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Friend notifications", fontSize = 20.sp, color = Color.White)
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

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 30.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Your profile",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Statistics",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Log out",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}