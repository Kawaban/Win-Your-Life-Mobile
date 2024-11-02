package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyVerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(330.dp)
            .background(MaterialTheme.colorScheme.onBackground)
    )
}
