package com.example.winyourlife.presentation.loginpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.util.ErrorScreen
import com.example.winyourlife.presentation.util.LoadingScreen
import com.example.winyourlife.presentation.util.MyHorizontalDivider
import com.example.winyourlife.presentation.util.OrangeButton
import com.example.winyourlife.presentation.util.TransparentButton
import com.example.winyourlife.presentation.util.WhiteOutlinedTextField

@Composable
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
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
                    LoginScreenMainContent(viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun LoginScreenMainContent(viewModel: LoginViewModel, navController: NavHostController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(300.dp)
                .height(205.dp)
                .padding(16.dp)
        ) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(email,{ email = it },"Email")

        WhiteOutlinedTextField(password,{ password = it },"Password", PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({ viewModel.login(email, password) }, "Log in")

        OrangeButton({
            viewModel.reset()
            navController.navigate(NavigationScreens.REGISTER.name)
        }, "Sign up")

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        TransparentButton({
            viewModel.reset()
            navController.navigate(NavigationScreens.FORGOT_PASSWORD.name)
        }, "Forgot your password?")

        Spacer(modifier = Modifier.height(50.dp))
    }
}
