package com.example.winyourlife.presentation.statisticspage

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.util.Headline
import com.example.winyourlife.presentation.util.MyHorizontalDivider

@Composable
fun StatisticsScreen(navController: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("STATISTICS")

        Spacer(modifier = Modifier.height(40.dp))

        MyHorizontalDivider()

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

        MyHorizontalDivider()

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

        MyHorizontalDivider()

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

        MyHorizontalDivider()

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
