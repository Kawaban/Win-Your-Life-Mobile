package com.example.winyourlife.presentation.forgotpasswordpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {
    override fun resetViewModel() { }

    var email = mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun resetPassword() { } //TODO
}