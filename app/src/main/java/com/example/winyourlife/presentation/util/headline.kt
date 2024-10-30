package com.example.winyourlife.presentation.util

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Headline(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 13.dp),
            fontWeight = FontWeight.Medium
        )
    }
}
