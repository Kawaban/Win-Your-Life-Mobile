package com.example.winyourlife.data.background

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.winyourlife.domain.UserPreferencesRepository

class CustomWorkerFactory(val userPreferencesRepository: UserPreferencesRepository) : WorkerFactory() {
    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {
        return when (workerClassName) {
            DailyReminderWorker::class.java.name -> DailyReminderWorker(userPreferencesRepository ,appContext, workerParameters)
            else -> null
        }
    }
}