package com.example.winyourlife.presentation.createtaskpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.CreateTaskRequest
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.utils.ImageEncoder
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(val currentUser: CurrentUser, val taskService: TaskService) : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() {
        state = State()
    }

    var taskName = mutableStateOf("")
        private set

    var taskImage = mutableStateOf(ByteArray(0))
        private set

    fun updateTaskName(newName: String) {
        taskName.value = newName
    }

    fun updateTaskImage(newImage: ByteArray) {
        taskImage.value = newImage
    }

    var state by mutableStateOf(State<Unit>())

    fun createTask(taskName: String, taskImage: ByteArray) {
        viewModelScope.launch {

            val task = CreateTaskRequest(taskName, ImageEncoder().encodeImageToString(taskImage))

            state = state.copy(
                isLoading = true,
                isReady = false
            )

            val result = taskService.createTask(task)

            state = when (result) {
                is Resource.Success -> {
                    state.copy(
                        isLoading = false,
                        isReady = true
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        isLoading = false,
                        isReady = true,
                        error = result.message
                    )
                }
            }
            currentUser.userData?.allTasks?.add(TaskData(taskName, taskImage,false))
        }
    }
}