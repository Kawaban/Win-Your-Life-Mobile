package com.example.winyourlife.presentation.addfriendpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResetPasswordScreen() {
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
                text = "Add a friend",
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
            text = "Enter the email address of the person you want to invite to your friends",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 60.dp),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Black
            )
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(60.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Send invitation",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.weight(1.6f))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    ResetPasswordScreen()
}