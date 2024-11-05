package com.example.winyourlife.presentation.resetpasswordpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(State<Nothing>())
        private set

    fun resetPassword(email: String, password: String) {}

    fun reset () {
        state = State()
    }
}