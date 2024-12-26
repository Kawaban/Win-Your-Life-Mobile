package com.example.winyourlife.presentation.registerpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ExceptionText
import com.example.winyourlife.presentation.utils.PasswordValidator
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authenticationService: AuthenticationService, val currentUser: CurrentUser): ViewModel(),
    ViewModelCustomInterface {

    var state by mutableStateOf(State<Unit>())
        private set

    var nickname = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    var repeatPassword = mutableStateOf("")
        private set

    fun updateNickname(newNickname: String) {
        nickname.value = newNickname
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateRepeatPassword(newRepeatPassword: String) {
        repeatPassword.value = newRepeatPassword
    }

    fun register(email: String, nick: String, password: String, repeatPassword: String) {

        viewModelScope.launch {

            if (!PasswordValidator().validatePassword(password)) {
                state = state.copy(
                    error = ExceptionText.PasswordMustContain.text,
                    isReady = true,
                    isLoading = false
                )
                return@launch
            }

            if (!PasswordValidator().checkPasswordsMatch(password, repeatPassword)) {
                state = state.copy(
                    error = ExceptionText.PasswordsDoNotMatch.text,
                    isReady = true,
                    isLoading = false
                )
                return@launch
            }

            val registerRequest = RegisterRequest(email, nick, password)

            state = state.copy(
                error = null,
                isReady = false,
                isLoading = true
            )

            val result = authenticationService.register(registerRequest)

            state = when (result) {
                is Resource.Success -> {
                    state.copy(
                        obj = result,
                        isReady = true,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isReady = true,
                        isLoading = false
                    )
                }
            }
        }
    }

    override fun resetViewModel () {
        state = State()
    }
}