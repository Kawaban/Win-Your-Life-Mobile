package com.example.winyourlife.presentation.profilepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.UserUpdateDataRequest
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ImageEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(val userService: UserService, val currentUser: CurrentUser) : ViewModel(),
    ViewModelCustomInterface {

    var stateUpdateData by mutableStateOf(State<Unit>())
        private set

    var isEditProfile by mutableStateOf(false)
        private set

    var nickname = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    fun updateNickname(newNickname: String) {
        nickname.value = newNickname
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun loadProfile() {
        nickname.value = currentUser.userData?.name ?: ""
        email.value = currentUser.userData?.email ?: ""
    }

    fun updateUserData(email: String, name: String, avatar: ByteArray) {
        viewModelScope.launch {
            val updateUserUpdateDataRequest =
                UserUpdateDataRequest(name, email, ImageEncoder().encodeImageToString(avatar))

            stateUpdateData = stateUpdateData.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = userService.updateUserData(updateUserUpdateDataRequest)
            stateUpdateData = when (result) {
                is com.example.winyourlife.domain.wrapper.Resource.Success -> {
                    stateUpdateData.copy(
                        obj = result,
                        isReady = true,
                        isLoading = false
                    )
                }
                is com.example.winyourlife.domain.wrapper.Resource.Error -> {
                    stateUpdateData.copy(
                        error = result.message,
                        isReady = true,
                        isLoading = false
                    )
                }
            }
            if (result is com.example.winyourlife.domain.wrapper.Resource.Success){
                currentUser.updateUserData(email, name, avatar)
            }
        }
    }

    fun editProfile() {
        isEditProfile = !isEditProfile
    }

    override fun resetViewModel(){
        isEditProfile = false
        stateUpdateData = State()
    }
}