package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(280.dp)
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.onBackground,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkedColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
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
