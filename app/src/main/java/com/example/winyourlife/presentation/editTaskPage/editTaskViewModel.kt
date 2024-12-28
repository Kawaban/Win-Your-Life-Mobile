package com.example.winyourlife.presentation.editTaskPage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.TaskUpdate
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.ImageEncoder
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(val currentUser: CurrentUser, val taskService: TaskService) : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() {
        state = State()
    }

    var state by mutableStateOf(State<Unit>())

    var taskName = mutableStateOf("")
        private set

    var taskImage = mutableStateOf(ByteArray(0))
        private set

    var taskOldName = mutableStateOf("")
        private set

    fun updateTaskName(newName: String) {
        taskName.value = newName
    }

    fun loadTask(taskIndex: Int) {
        val task = currentUser.userData?.allTasks?.getOrNull(taskIndex)

        if (task != null) {
            taskOldName.value = task.label
            taskName.value = task.label
            taskImage.value = task.image
        }
    }

    fun editTask() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            
            val result = taskService.updateTask(TaskUpdate(taskOldName.value,taskName.value, ImageEncoder().encodeImageToString(taskImage.value)))

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

            if (result is Resource.Success) {
                currentUser.userData?.allTasks?.find { it.label == taskOldName.value }?.image = taskImage.value
                currentUser.userData?.allTasks?.find { it.label == taskOldName.value }?.label = taskName.value

                currentUser.userData?.activeTasks?.find { it.label == taskOldName.value }?.image = taskImage.value
                currentUser.userData?.activeTasks?.find { it.label == taskOldName.value }?.label = taskName.value

                currentUser.userData?.preparedTasks?.find { it.label == taskOldName.value }?.image = taskImage.value
                currentUser.userData?.preparedTasks?.find { it.label == taskOldName.value }?.label = taskName.value
            }
        }
    }
}