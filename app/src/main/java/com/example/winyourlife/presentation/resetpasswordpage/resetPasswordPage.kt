package com.example.winyourlife.presentation.resetpasswordpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.utilScreens.ErrorScreen
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun ResetPasswordScreen(navController: NavHostController, viewModel: ResetPasswordViewModel = hiltViewModel()) {
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
                    ResetPasswordMainContent(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun ResetPasswordMainContent(navController: NavHostController, viewModel: ResetPasswordViewModel = hiltViewModel()) {

    var password by remember {
        mutableStateOf("")
    }

    var repeatPassword by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("RESET PASSWORD")

        Spacer(modifier = Modifier.height(50.dp))

        MyHorizontalDivider()

        Text(
            text = "Enter new password",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        Spacer(modifier = Modifier.weight(0.3f))

        WhiteOutlinedTextField(password,{ password = it },"Password", true, PasswordVisualTransformation())

        WhiteOutlinedTextField(repeatPassword,{ repeatPassword = it },"Repeat Password", true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({},"Change password")

        Spacer(modifier = Modifier.weight(1f))
    }
}
