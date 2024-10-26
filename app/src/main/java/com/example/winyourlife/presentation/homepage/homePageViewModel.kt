package com.example.winyourlife.presentation.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.domain.Resource
import com.example.winyourlife.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(val userService: UserService): ViewModel(){

    var state by mutableStateOf(State<UserResponse>())
        private set

    fun getUserName(){
        viewModelScope.launch {
            state = state.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = userService.getUser()
            state = when (result) {
                is Resource.Success -> {
                    state.copy(
                        obj = result,
                        isReady = true,
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isReady = true,
                        isLoading = true
                    )
                }
            }
        }
    }
}