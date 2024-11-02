package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyHorizontalDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.onBackground,
        thickness = 1.dp,
        modifier = Modifier
            .width(280.dp)
            .padding(vertical = 16.dp)
    )
}
