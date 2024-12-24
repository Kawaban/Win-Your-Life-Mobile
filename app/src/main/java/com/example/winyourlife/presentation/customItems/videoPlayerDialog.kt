package com.example.winyourlife.presentation.customItems

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.winyourlife.R
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerDialog(
    width: Int,
    height: Int,
    startPlaybackPosition: Long,
    onPlaybackPositionChange: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.one_chance)
            )
            setMediaItem(mediaItem)
            seekTo(startPlaybackPosition)
            prepare()
            playWhenReady = true
        }
    }

    LaunchedEffect(exoPlayer) {
        var lastPosition = startPlaybackPosition
        while (true) {
            val currentPosition = exoPlayer.currentPosition
            if (currentPosition != lastPosition) {
                onPlaybackPositionChange(currentPosition)
                lastPosition = currentPosition
            }
            delay(1000L)
        }
    }

    Dialog(onDismissRequest = {
        onDismiss()
    }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .widthIn(min = width.dp)
                .height(height.dp)
                .background(Color.Black)
        ) {
            Box(modifier = Modifier
                .width(width.dp)
                .height(height.dp)) {
                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = exoPlayer
                            useController = true
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        }
                    },
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black)
                )
            }

            DisposableEffect(exoPlayer) {
                onDispose {
                    exoPlayer.release()
                }
            }
        }
    }
}
