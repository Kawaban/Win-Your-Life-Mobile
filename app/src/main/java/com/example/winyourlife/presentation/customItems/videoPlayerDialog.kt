package com.example.winyourlife.presentation.customItems

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.winyourlife.R
import kotlinx.coroutines.delay

@Composable
fun VideoPlayerDialog(
    startPlaybackPosition: Long,
    onPlaybackPositionChange: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("video_player_prefs", Context.MODE_PRIVATE)
    val savedPosition = sharedPreferences.getLong("playback_position", startPlaybackPosition)

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.one_chance)
            )
            setMediaItem(mediaItem)
            seekTo(savedPosition)
            prepare()
            playWhenReady = true
        }
    }

    LaunchedEffect(exoPlayer) {
        while (true) {
            onPlaybackPositionChange(exoPlayer.currentPosition)
            sharedPreferences.edit().putLong("playback_position", exoPlayer.currentPosition).apply()
            delay(1000L)
        }
    }

    Dialog(onDismissRequest = {
        sharedPreferences.edit().putLong("playback_position", exoPlayer.currentPosition).apply()
        onDismiss()
    }) {
        Box(
            modifier = Modifier
                .width(360.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(170.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
            ) {
                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = exoPlayer
                            useController = true
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            DisposableEffect(exoPlayer) {
                onDispose {
                    sharedPreferences.edit().putLong("playback_position", exoPlayer.currentPosition).apply()
                    exoPlayer.release()
                }
            }
        }
    }
}