package com.example.winyourlife.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomStreak(
    isCompleted: Boolean,
    period: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFFFA500),
                shape = RoundedCornerShape(8.dp)
            )
            .width(280.dp)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isCompleted)
            {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier.size(24.dp)
                )
            }
            else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Star Icon",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "STREAK: ",
                fontSize = 16.sp,
                color = Color.White
            )

            Text(
                text = "$period days",
                fontSize = 16.sp,
                color = Color(0xFFFFA500)
            )
        }
    }
}
