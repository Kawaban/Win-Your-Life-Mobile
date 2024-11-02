package com.example.winyourlife.presentation.registerpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.RegisterRequest
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.domain.dto.Resource
import com.example.winyourlife.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val authenticationService: AuthenticationService): ViewModel(){

    var state by mutableStateOf(State<Nothing>())
        private set

    fun register(email: String, nick: String, password: String, repeatPassword: String) {

        viewModelScope.launch {
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

    fun reset () {
        state = State()
    }
}