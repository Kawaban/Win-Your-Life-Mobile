package com.example.winyourlife.data.background

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.winyourlife.R
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.utils.Settings
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DailyReminderWorker @AssistedInject constructor(
    val userPreferencesRepository: UserPreferencesRepository,
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    ): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        println("trying to send notification")
        val isCompleted = userPreferencesRepository.getParameter("isCompleted").getOrNull()
        val isDailyReminder = userPreferencesRepository.getParameter(Settings.IS_DAILY_REMINDER.name).getOrNull()
        if(isDailyReminder == "true" && isCompleted == "false"){
            val builder = NotificationCompat.Builder(appContext, "CHANNEL_ID")
                .setSmallIcon(R.drawable.appicon)
                .setContentTitle(appContext.getString(R.string.title_daily_reminder))
                .setContentText(appContext.getString(R.string.message_daily_reminder))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(appContext)) {
                if (ActivityCompat.checkSelfPermission(
                        appContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return@with
                }

                notify(1000, builder.build())
                println("notification sent")
            }
        }
        userPreferencesRepository.setParameter("isCompleted", "false")
        return Result.success()
    }




}