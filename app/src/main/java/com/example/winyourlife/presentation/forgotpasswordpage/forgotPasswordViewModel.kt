package com.example.winyourlife.presentation.forgotpasswordpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.winyourlife.presentation.utils.State


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(val authenticationService: AuthenticationService, val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {


    var state by mutableStateOf(State<Unit>())
        private set

    fun remindPassword(email: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                isReady = false
            )
            val result = authenticationService.remindPassword(email)
            state = when(result){
                is com.example.winyourlife.domain.wrapper.Resource.Success ->{
                    state.copy(
                        isLoading = false,
                        isReady = true
                    )
                }
                is com.example.winyourlife.domain.wrapper.Resource.Error -> {
                    state.copy(
                        isLoading = false,
                        isReady = true,
                        error = result.message
                    )
                }
            }

        }
    }


    override fun resetViewModel() {
        state = State()
    }
}