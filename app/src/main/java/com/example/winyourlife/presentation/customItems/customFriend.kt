package com.example.winyourlife.presentation.customItems

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.winyourlife.R

@Composable
fun CustomFriend(
    avatar: Int,
    nickname: String,
    isBetter: Boolean,
    period: String
) {
    Row(
        modifier = Modifier.width(320.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(280.dp)
                .padding(8.dp)
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = avatar),
                    contentDescription = stringResource(id = R.string.friends_avatar_description),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = nickname,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = period,
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

        IconButton(
            onClick = { },
            modifier = Modifier
                .weight(0.1f)
                .padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.remove_friend_description),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
