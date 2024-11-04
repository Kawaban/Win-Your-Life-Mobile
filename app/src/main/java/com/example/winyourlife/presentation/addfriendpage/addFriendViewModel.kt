package com.example.winyourlife.presentation.addfriendpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.winyourlife.presentation.ViewModelCustomInterface

@HiltViewModel
class AddFriendViewModel @Inject constructor() : ViewModel(), ViewModelCustomInterface {

    var emailSent by mutableStateOf(false)
        private set

    fun sendEmail() {
        emailSent = true
    }

    fun reset() {
        emailSent = false
    }

    override fun resetViewModel() {

    }
}