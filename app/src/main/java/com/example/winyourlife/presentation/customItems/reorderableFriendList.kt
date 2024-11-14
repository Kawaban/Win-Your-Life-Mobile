package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
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
import kotlin.math.abs

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
    val itemHeight = 60.dp
    var startIndex by remember { mutableIntStateOf(currentIndex) }
    var adjustedIndex by remember { mutableIntStateOf(-1) }
    var relativeOffsetY by remember { mutableFloatStateOf(0f) }
    var previousDragAmount by remember { mutableFloatStateOf(0f) }
    var isActive by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .background(
                if (isActive) Color.LightGray
                else MaterialTheme.colorScheme.background
            )
            .border(2.dp, Color.Gray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        isActive = true
                        println("nie Weszlem")
                    }
                )
            }
            .pointerInput(isActive) {
                println("Weszlem")
                detectDragGestures(
                    onDragStart = { _ ->
                        startIndex = currentIndex
                    },
                    onDrag = { _, dragAmount ->
                        relativeOffsetY += dragAmount.y - previousDragAmount

                        var shiftInElements = 0
                        if (relativeOffsetY >= 0) {
                            shiftInElements = (relativeOffsetY / (itemHeight + 8.dp).toPx()).toInt()
                        } else {
                            shiftInElements = (abs(relativeOffsetY) / (itemHeight + 8.dp).toPx()).toInt()
                            shiftInElements -= shiftInElements + shiftInElements
                        }

                        val targetIndex = (startIndex + shiftInElements).coerceIn(0, totalFriends - 1)

                        if (targetIndex != startIndex && targetIndex != adjustedIndex) {
                            onMove(startIndex, targetIndex)
                            adjustedIndex = targetIndex
                            startIndex = targetIndex
                            previousDragAmount = 0f
                            relativeOffsetY = 0f
                        }
                    },
                    onDragEnd = {
                        isActive = false
                        previousDragAmount = 0f
                        relativeOffsetY = 0f
                    }
                )
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text(text = friend.nickname, color = MaterialTheme.colorScheme.onBackground)
    }
}