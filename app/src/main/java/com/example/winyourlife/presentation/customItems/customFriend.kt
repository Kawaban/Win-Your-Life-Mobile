package com.example.winyourlife.presentation.customItems

import android.graphics.BitmapFactory
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.R
import java.util.Base64

@Composable
fun CustomFriend(
    avatar: ByteArray,
    nickname: String,
    isBetter: Boolean,
    period: Int,
    id: Int
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .width(280.dp)
            .padding(8.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            when {
                    avatar.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = stringResource(id = R.string.friends_avatar_description),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(16.dp)
                )
                else -> Image(
                    bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.size).asImageBitmap(),
                    contentDescription = stringResource(id = R.string.friends_avatar_description),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(16.dp)
                )
            }

            Text(
                text = nickname,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = period.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                if (isBetter) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.star_icon_description),
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.star_icon_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
