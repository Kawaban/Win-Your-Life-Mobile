package com.example.winyourlife.presentation.managefriendspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.dataObjects.FriendData
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.ReorderableFriendList
import com.example.winyourlife.presentation.customItems.SideNavigationBar

@Composable
fun ManageFriendsPage(navController: NavHostController) {
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
fun LandscapeLayout(navController: NavHostController, viewModel: ManageFriendsViewModel = hiltViewModel()) {

    val friends = listOf(
        FriendData(R.drawable.avatar, "Joe", false, "12", 0),
        FriendData(R.drawable.avatar, "Ellie", true, "32", 2),
        FriendData(R.drawable.avatar, "Alex", true, "45", 3),
        FriendData(R.drawable.avatar, "Sam", false, "28", 4),
        FriendData(R.drawable.avatar, "Chris", false, "53", 5),
        FriendData(R.drawable.avatar, "Sam", true, "28", 6),
        FriendData(R.drawable.avatar, "Chris", true, "53", 1)
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

            ReorderableFriendList(friends, 300)

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController)
    }
}


@Composable
fun PortraitLayout(navController: NavHostController, viewModel: ManageFriendsViewModel = hiltViewModel()) {

    val friends = listOf(
        FriendData(R.drawable.avatar, "Joe", false, "12", 0),
        FriendData(R.drawable.avatar, "Ellie", true, "32", 2),
        FriendData(R.drawable.avatar, "Alex", true, "45", 3),
        FriendData(R.drawable.avatar, "Sam", false, "28", 4),
        FriendData(R.drawable.avatar, "Chris", false, "53", 5),
        FriendData(R.drawable.avatar, "Sam", true, "28", 6),
        FriendData(R.drawable.avatar, "Chris", true, "53", 1)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.manage_friends_hd))

        Spacer(modifier = Modifier.weight(1f))

        ReorderableFriendList(friends, 520)

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}