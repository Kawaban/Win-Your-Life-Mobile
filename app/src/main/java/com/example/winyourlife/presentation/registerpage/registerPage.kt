package com.example.winyourlife.presentation.registerpage

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.TransparentButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.mapExceptionText
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun RegisterPage(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {

        val context = LocalContext.current

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
                        Toast.makeText(
                            context,
                            mapExceptionText(viewModel.state.error!!, context),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    false -> {
                        viewModel.resetViewModel()
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

    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
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

            WhiteOutlinedTextField(viewModel.nickname.value,{ viewModel.updateNickname(it) },stringResource(id = R.string.nickname_label), true)

            WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

            WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

            WhiteOutlinedTextField(viewModel.repeatPassword.value,{ viewModel.updateRepeatPassword(it) },stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton(
                {
                    viewModel.register(
                    viewModel.email.value, viewModel.nickname.value,
                    viewModel.password.value, viewModel.repeatPassword.value)
                },
                stringResource(id = R.string.register_button)
            )

            Spacer(modifier = Modifier.weight(0.4f))

            MyHorizontalDivider()

            TransparentButton({ navController.navigate(NavigationScreens.LOGIN.name) }, stringResource(id = R.string.register_text))

            Spacer(modifier = Modifier.weight(0.4f))
        }
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.register_hd))

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(viewModel.nickname.value,{ viewModel.updateNickname(it) },stringResource(id = R.string.nickname_label), true)

        WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

        WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

        WhiteOutlinedTextField(viewModel.repeatPassword.value,{ viewModel.updateRepeatPassword(it) },stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton(
            {
                viewModel.register(
                    viewModel.email.value, viewModel.nickname.value,
                    viewModel.password.value, viewModel.repeatPassword.value)
            },
            stringResource(id = R.string.register_button)
        )
        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        TransparentButton({ navController.navigate(NavigationScreens.LOGIN.name) }, stringResource(id = R.string.register_text))

        Spacer(modifier = Modifier.height(50.dp))
    }
}
