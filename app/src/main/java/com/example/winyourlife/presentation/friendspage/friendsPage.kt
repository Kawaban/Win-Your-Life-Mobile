package com.example.winyourlife.presentation.friendspage

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.FriendList
import com.example.winyourlife.presentation.dataObjects.FriendData
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun FriendsPage(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {
//    ResponsiveLayout(navController)
    WinYourLifeTheme(darkTheme = viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DARK_THEME.name)
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()
    ){
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
                        ResponsiveLayout(
                            navController
                        )
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

//    val friends = listOf(
//        FriendData(R.drawable.avatar, "Joe", false, "12", 0),
//        FriendData(R.drawable.avatar, "Ellie", true, "32", 2),
//        FriendData(R.drawable.avatar, "Alex", true, "45", 3),
//        FriendData(R.drawable.avatar, "Sam", false, "28", 4),
//        FriendData(R.drawable.avatar, "Chris", false, "53", 5),
//        FriendData(R.drawable.avatar, "Sam", true, "28", 6),
//        FriendData(R.drawable.avatar, "Chris", true, "53", 1)
//    )

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

//    val friends = listOf(
//        FriendData(R.drawable.avatar, "Joe", false, "12", 0),
//        FriendData(R.drawable.avatar, "Ellie", true, "32", 2),
//        FriendData(R.drawable.avatar, "Alex", true, "45", 3),
//        FriendData(R.drawable.avatar, "Sam", false, "28", 4),
//        FriendData(R.drawable.avatar, "Chris", false, "53", 5),
//        FriendData(R.drawable.avatar, "Sam", true, "28", 6),
//        FriendData(R.drawable.avatar, "Chris", true, "53", 1)
//    )

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