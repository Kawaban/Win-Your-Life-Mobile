package com.example.winyourlife.presentation.forgotpasswordpage

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun ForgotPasswordPage(navController: NavHostController, viewModel: ForgotPasswordViewModel = hiltViewModel()) {

    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout()
    }
    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }

    val context = LocalContext.current

    when (viewModel.state.isReady && viewModel.state.error == null) {
        true -> {
            Toast.makeText(context, stringResource(id = R.string.email_sent_snack), Toast.LENGTH_SHORT)
                .show()
        }

        false -> {
            when (viewModel.state.error != null) {
                true -> {
                    Toast.makeText(context, viewModel.state.error, Toast.LENGTH_SHORT).show()
                    viewModel.resetViewModel()
                }
                false -> {
                    // do nothing
                }
            }
        }
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
fun LandscapeLayout(viewModel: ForgotPasswordViewModel = hiltViewModel()) {

    var email by remember {
        mutableStateOf("")
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

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.forgot_password_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.width(250.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(email,{ email = it },stringResource(id = R.string.email_label), true)

            OrangeButton({viewModel.remindPassword(email)}, stringResource(id = R.string.send_email_button))

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PortraitLayout(viewModel: ForgotPasswordViewModel = hiltViewModel()) {

    var email by remember {
        mutableStateOf("")
    }

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
            text = stringResource(id = R.string.forgot_password_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(60.dp))

        WhiteOutlinedTextField(email,{ email = it },stringResource(id = R.string.email_label), true)

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({viewModel.remindPassword(email)}, stringResource(id = R.string.send_email_button))

        Spacer(modifier = Modifier.weight(1f))
    }
}
