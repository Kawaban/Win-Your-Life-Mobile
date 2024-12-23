package com.example.winyourlife

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.winyourlife.data.background.CustomWorkerFactory
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WinYourLifeApplication() : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()


    override fun onCreate() {
        super.onCreate()

        WorkManager.initialize(this, workManagerConfiguration)
    }
}