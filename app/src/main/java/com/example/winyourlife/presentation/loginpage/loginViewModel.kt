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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val authenticationService: AuthenticationService, val currentUser: CurrentUser) : ViewModel() {

    var state by mutableStateOf(State<Unit>())
        private set

    fun login(email: String, password: String) {

        viewModelScope.launch {
            val loginRequest = LoginRequest(email, password)

            state = state.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            println("result.message")
            val result = authenticationService.login(loginRequest)
            delay(100)
            println("lol" + result.message)
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

    fun reset () {
        state = State()
    }
}