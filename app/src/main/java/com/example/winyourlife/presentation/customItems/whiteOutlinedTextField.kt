package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

@Composable
fun WhiteOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isEditable: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var labelIsFocused by remember { mutableStateOf(false) }

    val labelColor = when {
        value.isEmpty() && !labelIsFocused -> Color.Black
        else -> MaterialTheme.colorScheme.onBackground
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = labelColor
        ) },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(280.dp)
            .onFocusChanged { focusState ->
                labelIsFocused = focusState.isFocused
            },
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
            disabledLabelColor = MaterialTheme.colorScheme.onBackground
        ),
        enabled = isEditable
    )
}
