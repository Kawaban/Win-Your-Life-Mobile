package com.example.winyourlife.presentation.addfriendpage

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
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.mapExceptionText
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun AddFriendPage(navController: NavHostController, viewModel: AddFriendViewModel = hiltViewModel()) {

    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {

        val context = LocalContext.current

        when (viewModel.stateSend.isReady && viewModel.stateSend.error == null) {
            true -> {
                viewModel.resetViewModel()
                navController.popBackStack()
                Toast.makeText(context,stringResource(id = R.string.request_sent_snack), Toast.LENGTH_SHORT).show()
            }
            false -> {
                when (viewModel.stateSend.error != null) {
                    true -> {
                        Toast.makeText(context, mapExceptionText(viewModel.stateSend.error!!,context), Toast.LENGTH_SHORT)
                            .show()
                        viewModel.resetViewModel()
                    }

                    false -> { }
                }
            }
        }

        ResponsiveLayout(navController)
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
fun LandscapeLayout(navController: NavHostController, viewModel: AddFriendViewModel = hiltViewModel()) {

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
                text = stringResource(id = R.string.add_friend_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.width(280.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({viewModel.addFriend(viewModel.email.value)}, stringResource(id = R.string.invite_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: AddFriendViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.add_friend_hd))

        Spacer(modifier = Modifier.height(50.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.add_friend_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.3f))

        WhiteOutlinedTextField(viewModel.email.value,{ viewModel.updateEmail(it) },stringResource(id = R.string.email_label), true)

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({viewModel.addFriend(viewModel.email.value) }, stringResource(id = R.string.invite_button))

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController, viewModel)
    }
}
