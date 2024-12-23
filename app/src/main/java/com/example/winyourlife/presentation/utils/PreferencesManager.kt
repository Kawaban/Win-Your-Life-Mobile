package com.example.winyourlife.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("video_player_prefs", Context.MODE_PRIVATE)

    fun savePlaybackPosition(position: Long) {
        sharedPreferences.edit().putLong("playback_position", position).apply()
    }

    fun getPlaybackPosition(defaultPosition: Long): Long {
        return sharedPreferences.getLong("playback_position", defaultPosition)
    }
}