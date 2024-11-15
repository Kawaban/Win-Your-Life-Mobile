package com.example.winyourlife.presentation.motivationpage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.winyourlife.R
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MotivationViewModel @Inject constructor(val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {

    private val _randomMotivation = MutableStateFlow<Pair<Int, Int>?>(null)
    val randomMotivation: StateFlow<Pair<Int, Int>?> = _randomMotivation

    private val _showVideoDialog = mutableStateOf(false)
    val showVideoDialog: MutableState<Boolean> = _showVideoDialog

    private val _startPlaybackPosition = mutableLongStateOf(0L)
    val startPlaybackPosition: MutableState<Long> = _startPlaybackPosition

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

    fun randomizeMotivation() {
        val currentLanguage = Locale.getDefault().language
        val mp3List = mp3Resources[currentLanguage] ?: mp3Resources["en"]

        if (mp3List == null || stringList.size != mp3List.size) {
            throw IllegalStateException("Lists of strings and MP3 resources must have the same size")
        }

        val randomIndex = (stringList.indices).random()

        _randomMotivation.value = Pair(stringList[randomIndex], mp3List[randomIndex])
    }

    override fun resetViewModel() {
        _showVideoDialog.value = false
        _startPlaybackPosition.longValue = 0L
    }

    fun updateShowVideoDialog(value: Boolean) {
        _showVideoDialog.value = value
    }

    fun updateStartPlaybackPosition(value: Long) {
        _startPlaybackPosition.longValue = value
    }
}