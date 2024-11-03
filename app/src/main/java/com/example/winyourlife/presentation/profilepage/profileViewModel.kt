package com.example.winyourlife.presentation.profilepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.presentation.ImageEncoder
import com.example.winyourlife.presentation.State
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val userService: UserService, val currentUser: CurrentUser) : ViewModel() {

    var stateUpdateData by mutableStateOf(State<Nothing>())
        private set

    fun updateUserData(email: String, name: String, avatar: ByteArray?) {
        viewModelScope.launch {
            val updateUserUpdateDataRequest =
                UserUpdateDataRequest(name, email, avatar?: byteArrayOf(0))

            stateUpdateData = stateUpdateData.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = userService.updateUserData(updateUserUpdateDataRequest)
            stateUpdateData = when (result) {
                is com.example.winyourlife.domain.dto.Resource.Success -> {
                    stateUpdateData.copy(
                        obj = result,
                        isReady = true,
                        isLoading = false
                    )
                }
                is com.example.winyourlife.domain.dto.Resource.Error -> {
                    stateUpdateData.copy(
                        error = result.message,
                        isReady = true,
                        isLoading = false
                    )
                }
            }
        }
    }
}