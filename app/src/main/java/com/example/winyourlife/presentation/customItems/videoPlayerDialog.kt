package com.example.winyourlife.presentation.customItems

import android.content.Context
import android.net.Uri
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
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.winyourlife.R
import kotlinx.coroutines.delay

@Composable
fun VideoPlayerDialog(
    width: Int,
    height: Int,
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
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .width(width.dp)
                .height(height.dp)
                .background(Color.Black)
        ) {
            Box {
                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = exoPlayer
                            useController = true
                        }
                    },
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize()
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