package com.example.winyourlife.presentation.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.winyourlife.data.dto.TaskCompletion
import com.example.winyourlife.data.dto.TaskResponse
import com.example.winyourlife.data.dto.UserResponse
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.domain.TaskService
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.domain.UserService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.State
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.utils.ImageEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val userService: UserService, val currentUser: CurrentUser, val userPreferencesRepository: UserPreferencesRepository, val jwtManager: JwtManager, val taskService: TaskService): ViewModel(),
    ViewModelCustomInterface {

    var state by mutableStateOf(State<UserResponse>())
        private set

    var stateTasks by mutableStateOf(State<List<TaskResponse>>())

    var tasks by mutableStateOf(currentUser.userData?.activeTasks?.sortedBy { taskData -> taskData.label } ?: listOf())

//    fun resetJwtManager(){
//        viewModelScope.launch {
//            jwtManager.resetJwt()
//        }
//    }

    fun getUserName() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                isReady = false,
                obj = null,
                error = null
            )
            if(jwtManager.isJwtSet())
            {
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
            else{
                delay(20)
                state = state.copy(
                    error = "notnolll",
                    isReady = true,
                    isLoading = false
                )

            }
            tasks = currentUser.userData?.activeTasks?.sortedBy { taskData -> taskData.label } ?: listOf()
        }
    }

//    fun getActiveTasks(){
//        viewModelScope.launch{
//            stateTasks = stateTasks.copy(
//                error = null,
//                isReady = false,
//                isLoading = true
//            )
//            val result = taskService.getActiveTasks()
//            stateTasks = when (result) {
//                is Resource.Success -> {
//                    stateTasks.copy(
//                        obj = result,
//                        isReady = true,
//                        isLoading = false
//                    )
//                }
//                is Resource.Error -> {
//                    stateTasks.copy(
//                        error = result.message,
//                        isReady = true,
//                        isLoading = false
//                    )
//                }
//            }
//
////            tasks = result.data?.map { TaskData(it.taskName, Base64.getDecoder().decode(it.taskImage),it.isCompleted) } ?: listOf()
//
//        }
//    }

    fun setTaskCompletion(taskName: String, status: Boolean){
        tasks.find { it.label == taskName }?.isCompleted = status

        currentUser.userData?.activeTasks?.find { it.label == taskName }?.isCompleted = status
    }


    override fun resetViewModel () {
        state = State()

        viewModelScope.launch {
            val result = currentUser.userData?.activeTasks?.map{mapTaskDataToTaskCompletion(it)}
            if(currentUser.userData?.activeTasks?.any { !it.isCompleted } == true){
                userPreferencesRepository.setParameter("isCompleted", "false")
            }
            else{
                userPreferencesRepository.setParameter("isCompleted", "true")
            }
            taskService.completeTasks(result?: listOf())

        }


    }

    private fun mapTaskDataToTaskCompletion(taskData: TaskData):TaskCompletion{
        return TaskCompletion(
            taskName = taskData.label,
            status = taskData.isCompleted
        )
    }



}