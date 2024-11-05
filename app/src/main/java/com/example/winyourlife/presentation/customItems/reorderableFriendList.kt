package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.dataObjects.FriendData

@Composable
fun ReorderableFriendList(initialFriends: List<FriendData>, height: Int) {
    var friends by remember { mutableStateOf(initialFriends) }

    LazyColumn(
        modifier = Modifier
            .width(280.dp)
            .padding(16.dp)
            .heightIn(max = height.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = friends, key = { _, friend -> friend.id }) { index, friend ->
            DraggableFriendItem(
                friend = friend,
                onMove = { fromIndex, toIndex ->
                    if (fromIndex != toIndex) {
                        friends = friends.toMutableList().apply {
                            val movedFriend = removeAt(fromIndex)
                            add(toIndex, movedFriend)
                        }
                    }
                },
                currentIndex = index,
                totalFriends = friends.size
            )
        }
    }
}

@Composable
fun DraggableFriendItem(
    friend: FriendData,
    onMove: (fromIndex: Int, toIndex: Int) -> Unit,
    currentIndex: Int,
    totalFriends: Int
) {
    var isDragging by remember { mutableStateOf(false) }
    var offsetY by remember { mutableStateOf(0f) }
    var adjustedIndex by remember { mutableStateOf(currentIndex) }
    val itemHeight = 56.dp
    val sensitivityFactor = 0.8f
    var startOffsetY by remember { mutableStateOf(0f) }
    var startIndex by remember { mutableStateOf(currentIndex) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .background(if (isDragging) Color.LightGray else MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging = true
                        startOffsetY = offsetY
                        startIndex = currentIndex
                    },
                    onDragEnd = {
                        isDragging = false
                        offsetY = 0f
                    },
                    onDragCancel = {
                        isDragging = false
                        offsetY = 0f
                    },
                    onDrag = { change, dragAmount ->
                        offsetY += dragAmount.y * sensitivityFactor

                        val relativeOffsetY = offsetY - startOffsetY
                        val targetIndex = (startIndex + (relativeOffsetY / itemHeight.toPx())).toInt()

                        if (targetIndex in 0 until totalFriends
                            && targetIndex != startIndex
                            && targetIndex != adjustedIndex) {

                            onMove(startIndex, targetIndex)
                            adjustedIndex = targetIndex
                            startIndex = targetIndex

                            change.consume()
                        }
                    }
                )
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = friend.nickname, color = MaterialTheme.colorScheme.onBackground)
    }
}
