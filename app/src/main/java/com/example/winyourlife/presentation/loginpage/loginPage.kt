package com.example.winyourlife.presentation.loginpage

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
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
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
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
                        ResponsiveLayout(viewModel, navController)
                    }
                }
            }
        }
    }

    BackHandler {
        viewModel.resetViewModel()
    }
}

@Composable
fun ResponsiveLayout(viewModel: LoginViewModel, navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitLayout(viewModel, navController)
    } else {
        LandscapeLayout(viewModel, navController)
    }
}

@Composable
fun LandscapeLayout(viewModel: LoginViewModel, navController: NavHostController) {

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

            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(145.dp)
                    .padding(16.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.app_logo_description),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

            WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({ viewModel.login(viewModel.email.value, viewModel.password.value) }, stringResource(id = R.string.log_in_button))

            OrangeButton({
                navController.navigate(NavigationScreens.REGISTER.name)
            }, stringResource(id = R.string.sign_up_button))

            Spacer(modifier = Modifier.weight(0.4f))

            MyHorizontalDivider()

            TransparentButton({
                navController.navigate(NavigationScreens.FORGOT_PASSWORD.name)
            }, stringResource(id = R.string.forgot_password_button))

            Spacer(modifier = Modifier.weight(0.4f))
        }
    }
}

@Composable
fun PortraitLayout(viewModel: LoginViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
                    contentDescription = stringResource(id = R.string.app_logo_description),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

        WhiteOutlinedTextField(viewModel.password.value,{ viewModel.updatePassword(it) },stringResource(id = R.string.password_label), true, PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({ viewModel.login(viewModel.email.value, viewModel.password.value) }, stringResource(id = R.string.log_in_button))

        OrangeButton({
            navController.navigate(NavigationScreens.REGISTER.name)
        }, stringResource(id = R.string.sign_up_button))

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        TransparentButton({
            navController.navigate(NavigationScreens.FORGOT_PASSWORD.name)
        }, stringResource(id = R.string.forgot_password_button))

        Spacer(modifier = Modifier.height(50.dp))
    }
}
