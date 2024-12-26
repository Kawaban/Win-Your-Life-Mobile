package com.example.winyourlife.presentation.friendspage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.FriendResponse
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.FriendData
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(private val userService: UserService, val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() { }

    var friendList by mutableStateOf(listOf<FriendData>())

    var stateFriends by mutableStateOf(State<List<FriendResponse>>())
        private set

    private fun mapFriendResponseToFriendData(friendResponse: List<FriendResponse>): List<FriendData> {
        return friendResponse.map { it ->
            FriendData(
                avatar = Base64.getDecoder().decode(it.avatar),
                nickname = it.name,
                id = friendResponse.indexOf(it),
                period = it.longestStreak,
                isBetter = (currentUser.userData?.longestStreak ?: 0) < it.longestStreak
            )
        }
    }

    fun getFriends(){
        viewModelScope.launch {
            stateFriends = stateFriends.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = userService.getFriends()
            stateFriends = when(result){
                is Resource.Success ->{
                    friendList = mapFriendResponseToFriendData(result.data?: listOf())
                    stateFriends.copy(
                        obj = result,
                        isReady = true,
                        isLoading = false
                    )
                }
                is Resource.Error ->{
                     stateFriends.copy(
                         error = result.message,
                         isReady = true,
                         isLoading = false
                     )
                }
            }
        }
    }
}