package com.example.winyourlife.presentation.registerpage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.winyourlife.presentation.customItems.TransparentButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun RegisterScreen(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    when (viewModel.state.isReady) {
        false -> {
            when(viewModel.state.isLoading){
                true ->{
                    LoadingScreen()
                }
                false -> {
                    RegisterMainContent(viewModel, navController)
                }
            }
        }
        true -> {
            when (viewModel.state.error != null) {
                true -> {
                    ErrorScreen(message = viewModel.state.error)
                }
                false -> {
                    viewModel.reset()
                    val context = LocalContext.current
                    Toast.makeText(context,"Account has been created", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavigationScreens.LOGIN.name)
                }
            }
        }
    }
}

@Composable
fun RegisterMainContent(viewModel: RegisterViewModel, navController: NavHostController) {

    var nickname by remember {
        mutableStateOf ("")
    }

    var email by remember {
        mutableStateOf ("")
    }

    var password by remember {
        mutableStateOf ("")
    }

    var repeatPassword by remember {
        mutableStateOf ("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("CREATE AN ACCOUNT")

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(nickname,{ nickname = it },"Nickname", true)

        WhiteOutlinedTextField(email,{ email = it },"Email", true)

        WhiteOutlinedTextField(password,{ password = it },"Password", true, PasswordVisualTransformation())

        WhiteOutlinedTextField(repeatPassword,{ repeatPassword = it },"Repeat Password", true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({viewModel.register(email, nickname, password, repeatPassword)}, "Register")

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        TransparentButton({
            viewModel.reset()
            navController.navigate(NavigationScreens.LOGIN.name)
        }, "Already have an account?")

        Spacer(modifier = Modifier.height(50.dp))
    }
}
