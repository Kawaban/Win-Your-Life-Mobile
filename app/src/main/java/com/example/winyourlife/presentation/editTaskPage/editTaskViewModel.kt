package com.example.winyourlife.presentation.editTaskPage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(val currentUser: CurrentUser, val taskService: TaskService) : ViewModel(), ViewModelCustomInterface {
    override fun resetViewModel() {
        state = State()
    }

    var state by mutableStateOf(State<Unit>())
}