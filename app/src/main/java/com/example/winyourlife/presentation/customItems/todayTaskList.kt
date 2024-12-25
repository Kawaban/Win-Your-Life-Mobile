package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.homepage.HomeViewModel

@Composable
fun TodayTaskList(viewModel: HomeViewModel, height: Int) {
    val tasks by viewModel.items.collectAsState()

    LazyColumn(
        modifier = Modifier
            .width(316.dp)
            .padding(16.dp)
            .heightIn(max = height.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(tasks) { index, task ->
            TodayTask(
                isComplete = task.isCompleted,
                label = task.label,
                image = task.image,
                toggleTaskCompletion = { viewModel.toggleTaskCompletion(index) }
            )
        }
    }
}