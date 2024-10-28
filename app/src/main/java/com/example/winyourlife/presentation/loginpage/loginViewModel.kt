package com.example.winyourlife.presentation.loginpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LoginViewModelInterface {
    var state: State<Any>
    fun login(email: String, password: String)
}

@HiltViewModel
class LoginViewModel @Inject constructor(val authenticationService: AuthenticationService) : ViewModel(), LoginViewModelInterface {
    private val _state = mutableStateOf(State<Any>())
    override var state: State<Any>
        get() = _state.value
        set(value) {
            _state.value = value
        }

    override fun login(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(email, password)

            state = state.copy(
                error = null,
                isReady = false
            )

            val result = authenticationService.login(loginRequest)
            state = when (result) {
                is com.example.winyourlife.domain.Resource.Success -> {
                    state.copy(
                        obj = result,
                        isReady = true
                    )
                }
                is com.example.winyourlife.domain.Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isReady = true
                    )
                }
            }
        }
    }
}

class FakeLoginViewModel : LoginViewModelInterface {
    override var state = State<Any>(isReady = true, error = null)

    override fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            state = State(isReady = true, error = "Email and password cannot be empty.")
        } else {
            state = State(isReady = true, error = null)
        }
    }
}
