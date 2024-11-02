package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.winyourlife.presentation.dataObjects.TaskData

@Composable
fun ReorderAbleTaskList(initialGoals: List<TaskData>, height: Int) {

    var tasks by remember { mutableStateOf(initialGoals) }

    LazyColumn(

        modifier = Modifier
            .width(316.dp)
            .padding(16.dp)
            .heightIn(max = height.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks.size) { index ->
            val task = tasks[index]

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = { },
                            onDragCancel = { },
                            onDrag = { change, dragAmount ->
                                change.consume()

                                val targetIndex = (index + dragAmount.y.toInt() / 60).coerceIn(0, tasks.size - 1)
                                if (targetIndex != index) {
                                    tasks = tasks.toMutableList().apply {
                                        add(targetIndex, removeAt(index))
                                    }
                                }
                            }
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.background)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = task.label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
