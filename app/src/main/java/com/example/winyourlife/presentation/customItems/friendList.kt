package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.dataObjects.FriendData

@Composable
fun FriendList(friends: List<FriendData>, height: Int) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = height.dp),
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