package com.example.winyourlife.presentation.addfriendpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.winyourlife.data.dto.FriendRequestCreate
import com.example.winyourlife.domain.NotificationService
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import kotlinx.coroutines.launch

@HiltViewModel
class AddFriendViewModel @Inject constructor(val notificationService: NotificationService) : ViewModel(), ViewModelCustomInterface {


    override fun resetViewModel() {

    }


    fun addFriend(email: String) {
        viewModelScope.launch {
            notificationService.sendFriendRequest(FriendRequestCreate(email))
        }
    }
}