package com.example.winyourlife.presentation.notificationspage

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.R
import com.example.winyourlife.data.dto.FriendRequestResponse
import com.example.winyourlife.data.dto.NotificationResponse
import com.example.winyourlife.domain.NotificationService
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.NotificationData
import com.example.winyourlife.presentation.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(val currentUser: CurrentUser, val notificationService: NotificationService) : ViewModel(),
    ViewModelCustomInterface {
    override fun resetViewModel() {
        notificationList = listOf()
        stateNotifications = State()
    }

    var notificationList by mutableStateOf(listOf<NotificationData>())

    var stateNotifications by mutableStateOf(State<List<NotificationResponse>>())
        private set

    fun mapNotificationResponseToNotificationData(notificationResponse: List<NotificationResponse>, context: Context): List<NotificationData> {
        return notificationResponse.map { it ->
            when(it.type){
                "FRIEND_REQUEST" -> NotificationData(
                    id = it.id,
                    time = it.time,
                    message = it.nickSender+" " +context.getString(R.string.notification_friend_request),
                    hasActions = true,
                    onClose = { notificationId -> notificationList = notificationList.filter { it.id != notificationId } },
                    onAccept = { notificationId ->acceptNotification(notificationId as UUID) },
                    onReject = { notificationId -> declineNotification(notificationId as UUID) }
                )
                "FRIEND_REQUEST_ACCEPTED" -> NotificationData(
                    id = it.id,
                    time = it.time,
                    message = it.nickSender+" " +context.getString(R.string.notification_friend_answer_positive),
                    hasActions = false,
                    onClose = { notificationId -> notificationList = notificationList.filter { it.id != notificationId } }
                )
                "FRIEND_REQUEST_DECLINED" -> NotificationData(
                    id = it.id,
                    time = it.time,
                    message = it.nickSender+" " +context.getString(R.string.notification_friend_answer_negative),
                    hasActions = false,
                    onClose = { notificationId -> notificationList = notificationList.filter { it.id != notificationId } }
                )
                else -> NotificationData(
                    id = it.id,
                    time = it.time,
                    message = it.type,
                    hasActions = false,
                    onClose = { notificationId -> notificationList = notificationList.filter { it.id != notificationId } }
                )
            }
        }
    }

    fun getNotifications(context: Context) {
        viewModelScope.launch {
            stateNotifications = stateNotifications.copy(
                error = null,
                isReady = false,
                isLoading = true
            )
            val result = notificationService.getNotifications()
            stateNotifications = when (result) {
                is com.example.winyourlife.domain.wrapper.Resource.Success -> {
                    stateNotifications.copy(
                        obj = result,
                        isReady = true,
                        isLoading = false
                    )
                }

                is com.example.winyourlife.domain.wrapper.Resource.Error -> {
                    stateNotifications.copy(
                        error = result.message,
                        isReady = true,
                        isLoading = false
                    )
                }
            }
            if(result is com.example.winyourlife.domain.wrapper.Resource.Success)
                notificationList = mapNotificationResponseToNotificationData(result.data?: listOf(),context)
        }
    }

    fun acceptNotification(notificationId: UUID) {
        viewModelScope.launch {
            val result = notificationService.acceptFriendRequest(FriendRequestResponse(notificationId))
            if(result is com.example.winyourlife.domain.wrapper.Resource.Success){
                notificationList = notificationList.filter { it.id != notificationId }
            }
        }
    }

    fun declineNotification(notificationId: UUID) {
        viewModelScope.launch {
            val result = notificationService.declineFriendRequest(FriendRequestResponse(notificationId))
            if(result is com.example.winyourlife.domain.wrapper.Resource.Success){
                notificationList = notificationList.filter { it.id != notificationId }
            }
        }
    }


}