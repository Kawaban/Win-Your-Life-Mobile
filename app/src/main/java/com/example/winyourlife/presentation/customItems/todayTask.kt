package com.example.winyourlife.presentation.customItems

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.R
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import com.example.winyourlife.presentation.homepage.HomeViewModel
import kotlinx.coroutines.delay
import java.util.Base64

@Composable
fun TodayTask(
    isComplete: Boolean,
    label: String,
    image: ByteArray,
    viewModel: HomeViewModel
) {

    var isCompletedx by remember { mutableStateOf(isComplete) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isCompletedx) MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.secondary
    )

    var targetPadding by remember { mutableStateOf(8.dp) }

    val animatedPadding by animateDpAsState(
        targetValue = targetPadding,
        animationSpec = keyframes {
            durationMillis = 300
        }
    )

    LaunchedEffect(isCompletedx) {
        targetPadding = 14.dp
        delay(200)
        targetPadding = 8.dp
    }

    Box(
        modifier = Modifier
            .clickable { isCompletedx = !isCompletedx;viewModel.setTaskCompletion(label,isCompletedx) }
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
                checked = isCompletedx,
                onCheckedChange = { checked ->
                    isCompletedx = checked;
                    viewModel.setTaskCompletion(label,isCompletedx)
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

            when {
                image.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = stringResource(id = R.string.friends_avatar_description),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
                else -> Image(
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.size).asImageBitmap(),
                    contentDescription = stringResource(id = R.string.friends_avatar_description),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}
