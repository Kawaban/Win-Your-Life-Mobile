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

    fun editTask(taskImage: ByteArray, taskName: String){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            val result = taskService.updateTask(TaskUpdate(taskName, ImageEncoder().encodeImageToString(taskImage)))
            state = when(result){
                is Resource.Success ->{
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
                currentUser.userData?.allTasks?.find { it.label == taskName }?.image = taskImage
                currentUser.userData?.activeTasks?.find { it.label == taskName }?.image = taskImage
                currentUser.userData?.preparedTasks?.find { it.label == taskName }?.image = taskImage
            }
        }
    }
}