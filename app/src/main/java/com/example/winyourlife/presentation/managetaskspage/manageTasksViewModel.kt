package com.example.winyourlife.presentation.managetaskspage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.winyourlife.data.dto.TaskPreparation
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
        prepareTasks()
        state = State()
    }

    var state by mutableStateOf(State<Unit>())
        private set

    var navController by mutableStateOf<NavController?>(null)
        private set

    fun updateNavController(controller: NavController) {
        navController = controller
    }

    private val _items = MutableStateFlow<List<TaskData>>(listOf())
    private val items: StateFlow<List<TaskData>> = _items

    fun loadTasks() {
        if (currentUser.userData == null) {
            _items.value = emptyList()
        } else {
            _items.value = currentUser.userData?.allTasks ?: emptyList()
        }
    }

    fun removeTask(position: Int) {//TODO
        _items.value = _items.value.toMutableList().apply { removeAt(position) }
    }

    fun getTasks(): StateFlow<List<TaskData>> {
        return items
    }

    fun editTask(index: Int) {
        navController?.navigate(NavigationScreens.EDIT_TASK.name + "/$index")
    }

    private fun prepareTasks() {//TODO what does this do?

        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            val ta = TaskPreparation(currentUser.userData?.allTasks?.map { it.label }?: listOf())
            val result = taskService.prepareTasks(ta)

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
    }
}