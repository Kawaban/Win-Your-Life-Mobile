package com.example.winyourlife.presentation.homepage

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.winyourlife.R
import com.example.winyourlife.data.dto.TaskCompletion
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.TaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userService: UserService,
                                        val currentUser: CurrentUser,
                                        val userPreferencesRepository: UserPreferencesRepository,
                                        val jwtManager: JwtManager,
                                        val taskService: TaskService): ViewModel(), ViewModelCustomInterface {

    var state by mutableStateOf(State<UserResponse>())
        private set

    private val _items = MutableStateFlow<List<TaskData>>(emptyList())
    val items: StateFlow<List<TaskData>> = _items

    var dayCompleted by mutableStateOf(false)
        private set

    private var taskCompletionHandled: Boolean = true

    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(context: Context) {
        if (mediaPlayer?.isPlaying == true) return

        mediaPlayer?.stop()
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, R.raw.day_won).apply {
            setOnCompletionListener {
                release()
                mediaPlayer = null
                taskCompletionHandled = false
                dayCompleted = false
            }
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        taskCompletionHandled = false
        dayCompleted = false
    }

    fun getUserName() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                isReady = false,
                obj = null,
                error = null
            )
            if (jwtManager.isJwtSet()) {
                val result = userService.getUser()
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
            else {
                delay(20)
                state = state.copy(
                    error = "notNull",
                    isReady = true,
                    isLoading = false
                )
            }
            _items.value = currentUser.userData?.activeTasks?.sortedBy { taskData -> taskData.label } ?: listOf()
        }
    }

    fun toggleTaskCompletion(index: Int) {
        val task = _items.value.getOrNull(index) ?: return
        task.isCompleted = !task.isCompleted
        currentUser.userData?.activeTasks?.find { it.label == task.label }?.isCompleted = task.isCompleted

        if (taskCompletionHandled && currentUser.userData?.activeTasks?.all { it.isCompleted } == true) {
            dayCompleted = true
            taskCompletionHandled = false
        }

        viewModelScope.launch {
            val result = currentUser.userData?.activeTasks?.map { mapTaskDataToTaskCompletion(it) }

            userPreferencesRepository.setParameter("isCompleted", dayCompleted.toString())
            taskService.completeTasks(result ?: listOf())
        }
    }

    private fun mapTaskDataToTaskCompletion(taskData: TaskData): TaskCompletion {
        return TaskCompletion(
            taskName = taskData.label,
            status = taskData.isCompleted
        )
    }

    override fun resetViewModel() {
        if (mediaPlayer?.isPlaying == true) {
            stopAudio()
        }
        state = State()
    }
}