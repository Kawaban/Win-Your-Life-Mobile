package com.example.winyourlife.presentation.statisticspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SettingsScreen() {
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
                text = "Statistics",
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
            text = "Longest streak",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "37 days",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(240.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1A1A),
                unfocusedContainerColor = Color(0xFF1A1A1A),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFFFA500),
                unfocusedBorderColor = Color(0xFFFFA500),
                cursorColor = Color.White
            )
        )
        
        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Number of tasks completed",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "1112",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(240.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1A1A),
                unfocusedContainerColor = Color(0xFF1A1A1A),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFFFA500),
                unfocusedBorderColor = Color(0xFFFFA500),
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Number of friends",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "4",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(240.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1A1A),
                unfocusedContainerColor = Color(0xFF1A1A1A),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFFFA500),
                unfocusedBorderColor = Color(0xFFFFA500),
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "You've been with us for ...",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "210 days!",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(240.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1A1A),
                unfocusedContainerColor = Color(0xFF1A1A1A),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFFFA500),
                unfocusedBorderColor = Color(0xFFFFA500),
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}