package com.example.winyourlife.presentation.util

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyHorizontalDivider() {
    HorizontalDivider(
        color = Color.White,
        thickness = 1.dp,
        modifier = Modifier
            .width(280.dp)
            .padding(vertical = 16.dp)
    )
}
