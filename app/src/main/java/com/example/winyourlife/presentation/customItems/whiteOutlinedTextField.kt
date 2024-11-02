package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

@Composable
fun WhiteOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isEditable: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        ) },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.Black,
            disabledBorderColor = Color.White,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Black
        ),
        enabled = isEditable
    )
}
