package com.example.winyourlife.presentation.friendspage

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.FriendList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.mapExceptionText
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun FriendsPage(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {

    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {

        val context = LocalContext.current

        when (!viewModel.stateFriends.isLoading && !viewModel.stateFriends.isReady) {
            true -> {
                viewModel.getFriends()
            }

            false -> {
                when (viewModel.stateFriends.isLoading) {
                    true -> {
                        LoadingScreen()
                    }

                    false -> {
                        when (viewModel.stateFriends.error == null) {
                            true -> {
                                ResponsiveLayout(
                                    navController
                                )
                            }
                            false -> {
                                Toast.makeText(context, mapExceptionText(viewModel.stateFriends.error!!, context), Toast.LENGTH_SHORT).show()
                                viewModel.resetViewModel()
                            }
                        }
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
fun LandscapeLayout(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {

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

            FriendList(viewModel.friendList, 300)

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({ navController.navigate(NavigationScreens.ADD_FRIEND.name) }, stringResource(id = R.string.add_friend_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}


@Composable
fun PortraitLayout(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.friends_hd))

        Spacer(modifier = Modifier.height(90.dp))

        FriendList(viewModel.friendList, 380)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton({ navController.navigate(NavigationScreens.ADD_FRIEND.name) }, stringResource(id = R.string.add_friend_button))

        Spacer(modifier = Modifier.height(60.dp))

        BottomNavigationBar(navController, viewModel)
    }
}