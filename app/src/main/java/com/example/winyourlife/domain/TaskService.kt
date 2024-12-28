package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.CreateTaskRequest
import com.example.winyourlife.data.dto.TaskCompletion
import com.example.winyourlife.data.dto.TaskDelete
import com.example.winyourlife.data.dto.TaskPreparation
import com.example.winyourlife.data.dto.TaskResponse
import com.example.winyourlife.data.dto.TaskUpdate
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.performNetworkOperation
import com.example.winyourlife.domain.wrapper.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskService @Inject constructor(private val apiService: ApiService) {
    suspend fun createTask(taskRequest: CreateTaskRequest): Resource<Unit> {
        return performNetworkOperation {
            apiService.createTask(taskRequest)
        }
    }

    suspend fun getAllTasks(): Resource<List<TaskResponse>> {
        return performNetworkOperation {
            apiService.getAllTasks()
        }
    }

    suspend fun getActiveTasks(): Resource<List<TaskResponse>>{
        return performNetworkOperation {
            apiService.getActiveTasks()
        }
    }

    suspend fun getPreparedTasks(): Resource<List<TaskResponse>>{
        return performNetworkOperation {
            apiService.getPreparedTasks()
        }
    }

    suspend fun deleteTask(taskDelete: String): Resource<Unit> {
        return performNetworkOperation {
            apiService.deleteTask(taskDelete)
        }
    }

    suspend fun updateTask(taskUpdate: TaskUpdate): Resource<Unit> {
        return performNetworkOperation {
            apiService.updateTask(taskUpdate)
        }
    }

    suspend fun completeTasks(completedTasks: List<TaskCompletion>): Resource<Unit> {
        return performNetworkOperation {
            apiService.completeTask(completedTasks)
        }
    }

    suspend fun prepareTasks(preparedTask:TaskPreparation): Resource<Unit> {
        return performNetworkOperation {
            apiService.prepareTask(preparedTask)
        }
    }





}