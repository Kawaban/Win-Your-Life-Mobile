package com.example.winyourlife.domain

import com.example.winyourlife.data.dto.CreateTaskRequest
import com.example.winyourlife.data.dto.TaskResponse
import com.example.winyourlife.data.network.ApiService
import com.example.winyourlife.data.network.performNetworkOperation
import com.example.winyourlife.domain.wrapper.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskService @Inject constructor(val apiService: ApiService) {
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
}