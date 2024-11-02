package com.example.winyourlife.presentation.friendspage

import androidx.compose.foundation.background
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

@Composable
fun FriendsPage(navController: NavHostController) {
    ResponsiveLayout(navController)
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

    val friends = listOf(
        FriendData(R.drawable.avatar, "Joe", false, "12"),
        FriendData(R.drawable.avatar, "Ellie", true, "32"),
        FriendData(R.drawable.avatar, "Alex", true, "45"),
        FriendData(R.drawable.avatar, "Sam", true, "28"),
        FriendData(R.drawable.avatar, "Chris", true, "53"),
        FriendData(R.drawable.avatar, "Sam", true, "28"),
        FriendData(R.drawable.avatar, "Chris", true, "53")
    )

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

            FriendList(friends, 300)

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({ navController.navigate(NavigationScreens.ADD_FRIEND.name) }, "Add new friend")

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController)
    }
}


@Composable
fun PortraitLayout(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {

    val friends = listOf(
        FriendData(R.drawable.avatar, "Joe", false, "12"),
        FriendData(R.drawable.avatar, "Ellie", true, "32"),
        FriendData(R.drawable.avatar, "Alex", true, "45"),
        FriendData(R.drawable.avatar, "Sam", true, "28"),
        FriendData(R.drawable.avatar, "Chris", true, "53"),
        FriendData(R.drawable.avatar, "Sam", true, "28"),
        FriendData(R.drawable.avatar, "Chris", true, "53")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("YOUR FRIENDS")

        Spacer(modifier = Modifier.height(90.dp))

        FriendList(friends, 380)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton({ navController.navigate(NavigationScreens.ADD_FRIEND.name) }, "Add new friend")

        Spacer(modifier = Modifier.height(60.dp))

        BottomNavigationBar(navController)
    }
}