package com.example.winyourlife.presentation.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.domain.dto.Resource
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val userService: UserService, val currentUser: CurrentUser, val userPreferencesRepository: UserPreferencesRepository): ViewModel(),
    ViewModelCustomInterface {

    var state by mutableStateOf(State<UserResponse>())
        private set

    fun getUserName() {
        viewModelScope.launch {
            state = state.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = userService.getUser()

            //settings
            for(setting in Settings.entries){
                currentUser.userData?.mapOfSettings?.set(setting.name,
                    userPreferencesRepository.getParameter(setting.name).getOrNull()
                )
            }

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