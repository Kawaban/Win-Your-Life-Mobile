package com.example.winyourlife.presentation.resetpasswordpage

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.mapExceptionText
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun ResetPasswordPage(navController: NavHostController, viewModel: ResetPasswordViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {

        val context = LocalContext.current

        when (viewModel.state.isReady) {
            true -> {
                when (viewModel.state.error != null) {
                    true -> {
                        Toast.makeText(context, mapExceptionText(viewModel.state.error!!, context), Toast.LENGTH_SHORT).show()
                        viewModel.resetViewModel()
                    }

                    false -> {
                        viewModel.resetViewModel()
                        navController.navigate(NavigationScreens.HOME.name)
                    }
                }
            }

            false -> {
                when (viewModel.state.isLoading) {
                    true -> {
                        LoadingScreen()
                    }

                    false -> {
                        ResponsiveLayout()
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
fun ResponsiveLayout() {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
       PortraitLayout()
    } else {
        LandscapeLayout()
    }
}

@Composable
fun LandscapeLayout(viewModel: ResetPasswordViewModel = hiltViewModel()) {

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

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.reset_password_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },
                stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

            WhiteOutlinedTextField(viewModel.repeatPassword.value,{ viewModel.updateRepeatPassword(it) },stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({},stringResource(id = R.string.change_password_button))

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PortraitLayout(viewModel: ResetPasswordViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.reset_password_hd))

        Spacer(modifier = Modifier.height(50.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.reset_password_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        Spacer(modifier = Modifier.weight(0.3f))

        WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

        WhiteOutlinedTextField(viewModel.repeatPassword.value,{ viewModel.updateRepeatPassword(it) },stringResource(id = R.string.repeat_password_label), true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({},stringResource(id = R.string.change_password_button))

        Spacer(modifier = Modifier.weight(1f))
    }
}
