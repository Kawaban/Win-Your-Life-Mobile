package com.example.winyourlife.presentation.loginpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authenticationService: AuthenticationService, val currentUser: CurrentUser) : ViewModel(),
    ViewModelCustomInterface {


    var state by mutableStateOf(State<Unit>())
        private set

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun login(email: String, password: String) {

        viewModelScope.launch {
            val loginRequest = LoginRequest(email, password)

            state = state.copy(
                error = null,
                isReady = false,
                isLoading = true
            )

            val result = authenticationService.login(loginRequest)
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