package com.example.winyourlife.presentation.motivationpage

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.winyourlife.R
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.PreferencesManager
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MotivationViewModel @Inject constructor(val currentUser: CurrentUser,
                                              private val preferencesManager: PreferencesManager
) : ViewModel(), ViewModelCustomInterface {

    private val _startPlaybackPosition = MutableStateFlow(0L)
    val startPlaybackPosition: StateFlow<Long> = _startPlaybackPosition

    private val _randomMotivation = MutableStateFlow<Pair<Int, Int>?>(null)
    val randomMotivation: StateFlow<Pair<Int, Int>?> = _randomMotivation

    private val _showVideoDialog = MutableStateFlow(false)
    val showVideoDialog: StateFlow<Boolean> = _showVideoDialog


    private val stringList = listOf(
        R.string.motivation1, R.string.motivation2, R.string.motivation3,
        R.string.motivation4, R.string.motivation5, R.string.motivation6,
        R.string.motivation7, R.string.motivation8, R.string.motivation9, R.string.motivation10
    )

    private val mp3Resources = mapOf(
        "en" to listOf(
            R.raw.motivation1_en, R.raw.motivation2_en, R.raw.motivation3_en,
            R.raw.motivation4_en, R.raw.motivation5_en, R.raw.motivation6_en,
            R.raw.motivation7_en, R.raw.motivation8_en, R.raw.motivation9_en, R.raw.motivation10_en
        ),
        "pl" to listOf(
            R.raw.motivation1_pl, R.raw.motivation2_pl, R.raw.motivation3_pl,
            R.raw.motivation4_pl, R.raw.motivation5_pl, R.raw.motivation6_pl,
            R.raw.motivation7_pl, R.raw.motivation8_pl, R.raw.motivation9_pl, R.raw.motivation10_pl
        )
    )

    private var mediaPlayer: MediaPlayer? = null

    fun randomizeMotivation() {
        val currentLanguage = Locale.getDefault().language
        val mp3List = mp3Resources[currentLanguage] ?: mp3Resources["en"]

        if (mp3List == null || stringList.size != mp3List.size) {
            throw IllegalStateException("Lists of strings and MP3 resources must have the same size")
        }

        val randomIndex = (stringList.indices).random()
        _randomMotivation.value = Pair(stringList[randomIndex], mp3List[randomIndex])
    }

    fun playAudio(context: Context, resId: Int) {
        mediaPlayer?.stop()
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, resId).apply {
            setOnCompletionListener {
                release()
                mediaPlayer = null
            }
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    override fun resetViewModel() {
        if (isPlaying()) {
            stopAudio()
        }
        _showVideoDialog.value = false
        _startPlaybackPosition.value = 0L
    }

    fun updateShowVideoDialog(value: Boolean) {
        _showVideoDialog.value = value
    }

    fun updatePlaybackPosition(position: Long) {
        _startPlaybackPosition.value = position
        preferencesManager.savePlaybackPosition(position)
    }
}