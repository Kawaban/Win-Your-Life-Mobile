package com.example.winyourlife.presentation.managetaskspage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageTasksViewModel @Inject constructor(val currentUser: CurrentUser, private val taskService: TaskService) : ViewModel(), ViewModelCustomInterface {

    override fun resetViewModel() {
        state = State()
    }

    var state by mutableStateOf(State<Unit>())
        private set

    var navController by mutableStateOf<NavController?>(null)
        private set

    fun updateNavController(controller: NavController) {
        navController = controller
    }

    private val _items = MutableStateFlow<List<TaskData>>(emptyList())
    val items: StateFlow<List<TaskData>> = _items

    fun loadTasks() {
        if (currentUser.userData == null) {
            _items.value = emptyList()
        } else {
            _items.value = currentUser.userData?.allTasks ?: emptyList()
        }
    }

    fun removeTask(position: Int) {
        deleteTask(position)
        _items.value = _items.value.toMutableList().apply { removeAt(position) }
    }

    private fun deleteTask(position: Int) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            val taskLabel = _items.value[position].label
            val result = taskService.deleteTask(taskLabel)

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

            if (result is Resource.Success) {
                currentUser.userData?.allTasks?.removeIf { it.label == taskLabel }
                currentUser.userData?.preparedTasks?.removeIf { it.label == taskLabel }
                currentUser.userData?.activeTasks?.removeIf { it.label == taskLabel }
            }
        }
    }

    fun editTask(index: Int) {
        resetViewModel()
        navController?.navigate(NavigationScreens.EDIT_TASK.name + "/$index")
    }
}