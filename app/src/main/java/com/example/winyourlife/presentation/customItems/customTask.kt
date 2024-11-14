package com.example.winyourlife.presentation.customItems

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.R

@Composable
fun CustomGoal(
    isCompleted: Boolean,
    label: String,
    image: Int
) {
    var isCompleted by remember { mutableStateOf(isCompleted) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isCompleted) MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.secondary
    )

    var targetPadding by remember { mutableStateOf(8.dp) }

    val animatedPadding by animateDpAsState(
        targetValue = targetPadding,
        animationSpec = keyframes {
            durationMillis = 300
        }
    )

    LaunchedEffect(isCompleted) {
        targetPadding = 14.dp
        kotlinx.coroutines.delay(200)
        targetPadding = 8.dp
    }

    Box(
        modifier = Modifier
            .clickable { isCompleted = !isCompleted }
            .background(color = backgroundColor,
                shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(animatedPadding)
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(280.dp)
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { checked ->
                    isCompleted = checked
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.onBackground,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkedColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(start = 8.dp)
            )

            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = R.string.task_image_description),
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}
