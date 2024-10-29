package com.example.winyourlife.presentation.loginpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.util.ErrorScreen
import com.example.winyourlife.presentation.util.LoadingScreen

@Composable
fun loginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    when (viewModel.state.isReady) {
        true -> {
            when (viewModel.state.error != null) {
                true -> {
                    ErrorScreen(message = viewModel.state.error)
                }
                false -> {
                    viewModel.reset()
                    navController.navigate(NavigationScreens.HOME.name)
                }
            }
        }
        false -> {
            when(viewModel.state.isLoading) {
                true -> {
                    LoadingScreen()
                }
                false -> {
                    LoadingScreenMainContent(viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun LoadingScreenMainContent(viewModel: LoginViewModel, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 16.dp),
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

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 16.dp),
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
            onClick = {viewModel.login(email, password)},
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Log in", color = Color.White, fontSize = 20.sp)
        }

        Button(
            onClick = {
                viewModel.reset()
                navController.navigate(NavigationScreens.REGISTER.name)
            },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Sign up", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Forgot your password?",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        Button(
            onClick = {
                viewModel.reset()
                navController.navigate(NavigationScreens.LOGIN.name)
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Reset password",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
