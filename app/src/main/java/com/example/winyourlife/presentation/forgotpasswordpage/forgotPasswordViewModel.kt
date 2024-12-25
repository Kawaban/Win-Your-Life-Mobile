package com.example.winyourlife.presentation.forgotpasswordpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(val authenticationService: AuthenticationService) : ViewModel(), ViewModelCustomInterface {

    fun remindPassword(email: String) {
        viewModelScope.launch {
            authenticationService.remindPassword(email)
        }
    }


    override fun resetViewModel() {

    }
}