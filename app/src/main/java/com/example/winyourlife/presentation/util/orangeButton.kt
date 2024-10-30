package com.example.winyourlife.presentation.util

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

@Composable
fun OrangeButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(280.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 20.sp)
    }
}
