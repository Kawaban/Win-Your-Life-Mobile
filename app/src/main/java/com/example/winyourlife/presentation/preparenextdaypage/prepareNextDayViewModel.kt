package com.example.winyourlife.presentation.preparenextdaypage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.data.dto.TaskPreparation
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrepareNextDayViewModel @Inject constructor(val currentUser: CurrentUser, private val taskService: TaskService) : ViewModel(), ViewModelCustomInterface {

    var state by mutableStateOf(State<Unit>())
        private set

    private val _preparedTasks = MutableStateFlow<List<TaskData>>(emptyList())
    val preparedTasks: StateFlow<List<TaskData>> = _preparedTasks

    private val _allTasks = MutableStateFlow<List<TaskData>>(emptyList())
    val allTasks: StateFlow<List<TaskData>> = _allTasks

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun loadTasks() {
        val allTasks = currentUser.userData?.allTasks ?: listOf()
        val preparedTasks = currentUser.userData?.preparedTasks ?: listOf()
        val preparedTaskLabels = preparedTasks.map { it.label }

        _allTasks.value = allTasks.filter { task ->
            task.label !in preparedTaskLabels
        }
        _preparedTasks.value = preparedTasks
    }

    fun showDialog() {
        _showDialog.value = true
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    override fun resetViewModel() {
        state = State()
    }

    fun addTask(newItemIndex: Int) {
        _preparedTasks.value += _allTasks.value.toMutableList()[newItemIndex]
        _allTasks.value = _allTasks.value.toMutableList().apply { removeAt(newItemIndex) }
        prepareTasks()
    }

    fun removeTask(index: Int) {
        _preparedTasks.value = _preparedTasks.value.toMutableList().apply { removeAt(index) }
        prepareTasks()
    }

    private fun prepareTasks() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            val ta = TaskPreparation(_preparedTasks.value.map { it.label })
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
