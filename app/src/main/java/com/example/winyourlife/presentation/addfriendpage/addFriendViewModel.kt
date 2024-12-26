package com.example.winyourlife.presentation.addfriendpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.FriendRequestCreate
import com.example.winyourlife.domain.NotificationService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import kotlinx.coroutines.launch

@HiltViewModel
class AddFriendViewModel @Inject constructor(val notificationService: NotificationService, val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() {
        stateSend = State()
    }

    var email = mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    var stateSend by mutableStateOf(State<Unit>())

    fun addFriend(email: String) {
        viewModelScope.launch {
            stateSend = stateSend.copy(
                isLoading = true,
                isReady = false
            )
            val result = notificationService.sendFriendRequest(FriendRequestCreate(email))
            stateSend = when(result){
                is Resource.Success ->{
                    stateSend.copy(
                        isLoading = false,
                        isReady = true
                    )
                }
                is Resource.Error -> {
                    stateSend.copy(
                        isLoading = false,
                        isReady = true,
                        error = result.message
                    )
                }
            }
        }
    }
}