package com.example.winyourlife.presentation.motivationpage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MotivationViewModel @Inject constructor() : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() {
        _showVideoDialog.value = false
        _startPlaybackPosition.value = 0L
    }

    private val _showVideoDialog = mutableStateOf(false)
    val showVideoDialog: MutableState<Boolean> = _showVideoDialog

    private val _startPlaybackPosition = mutableStateOf(0L)
    val startPlaybackPosition: MutableState<Long> = _startPlaybackPosition

    fun updateShowVideoDialog(value: Boolean) {
        _showVideoDialog.value = value
    }

    fun updateStartPlaybackPosition(value: Long) {
        _startPlaybackPosition.value = value
    }
}