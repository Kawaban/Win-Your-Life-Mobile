package com.example.winyourlife.presentation.friendspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.CustomFriend
import com.example.winyourlife.presentation.dataObjects.FriendData
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.OrangeButton

@Composable
fun FriendsList(friends: List<FriendData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 380.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(friends) { friend ->
            CustomFriend(
                avatar = friend.avatar,
                nickname = friend.nickname,
                isBetter = friend.isBetter,
                period = friend.period
            )
        }
    }
}

@Composable
fun FriendsScreen(navController: NavHostController, viewModel: FriendsViewModel = hiltViewModel()) {

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
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("YOUR FRIENDS")

        Spacer(modifier = Modifier.height(90.dp))

        FriendsList(friends)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton({ navController.navigate(NavigationScreens.ADD_FRIEND.name) }, "Add new friend")

        Spacer(modifier = Modifier.height(60.dp))

        BottomNavigationBar(navController)
    }
}