package com.example.winyourlife.presentation.registerpage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.utilScreens.ErrorScreen
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.TransparentButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun RegisterPage(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme =  isSystemInDarkTheme()) {
        when (viewModel.state.isReady) {
            false -> {
                when (viewModel.state.isLoading) {
                    true -> {
                        LoadingScreen()
                    }

                    false -> {
                        ResponsiveLayout(navController)
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
                        Toast.makeText(
                            context,
                            stringResource(id = R.string.account_created_snack),
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(NavigationScreens.LOGIN.name)
                    }
                }
            }
        }
    }
}

@Composable
fun ResponsiveLayout(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
       PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }
}

@Composable
fun LandscapeLayout(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {

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

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(nickname,{ nickname = it },stringResource(id = R.string.nickname_label), true)

            WhiteOutlinedTextField(email,{ email = it },stringResource(id = R.string.email_label), true)

            WhiteOutlinedTextField(password,{ password = it },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

            WhiteOutlinedTextField(repeatPassword,{ repeatPassword = it },
                stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({viewModel.register(email, nickname, password, repeatPassword)}, stringResource(id = R.string.register_button))

            Spacer(modifier = Modifier.weight(0.4f))

            MyHorizontalDivider()

            TransparentButton({
                viewModel.reset()
                navController.navigate(NavigationScreens.LOGIN.name)
            }, stringResource(id = R.string.register_text))

            Spacer(modifier = Modifier.weight(0.4f))
        }
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {

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
        Headline(stringResource(id = R.string.register_hd))

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(nickname,{ nickname = it },stringResource(id = R.string.nickname_label), true)

        WhiteOutlinedTextField(email,{ email = it },stringResource(id = R.string.email_label), true)

        WhiteOutlinedTextField(password,{ password = it },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

        WhiteOutlinedTextField(repeatPassword,{ repeatPassword = it },stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({viewModel.register(email, nickname, password, repeatPassword)}, stringResource(id = R.string.register_button))

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        TransparentButton({
            viewModel.reset()
            navController.navigate(NavigationScreens.LOGIN.name)
        }, stringResource(id = R.string.register_text))

        Spacer(modifier = Modifier.height(50.dp))
    }
}
