package com.example.winyourlife.presentation.resetpasswordpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {
    var state by mutableStateOf(State<Nothing>())
        private set

    var password = mutableStateOf("")
        private set

    var repeatPassword = mutableStateOf("")
        private set

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateRepeatPassword(newRepeatPassword: String) {
        repeatPassword.value = newRepeatPassword
    }

    override fun resetViewModel() {
        state = State()
    }
}