package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color

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
            disabledTextColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            disabledBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = Color.Black,
            disabledLabelColor = Color.Black
        ),
        enabled = isEditable
    )
}
