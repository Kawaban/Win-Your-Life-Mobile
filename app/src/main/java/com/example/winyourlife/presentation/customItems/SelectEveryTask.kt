package com.example.winyourlife.presentation.customItems

import android.graphics.BitmapFactory
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import java.util.Base64

@Composable
fun SelectEveryTask(
    label: String,
    image: ByteArray,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
                .animateContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(start = 8.dp)
                )

                when {
                    image.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                        painter = painterResource(id = R.drawable.task),
                        contentDescription = stringResource(id = R.string.task_image_description),
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
}