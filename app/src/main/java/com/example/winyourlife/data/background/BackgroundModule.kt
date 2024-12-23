package com.example.winyourlife.data.background

import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.winyourlife.domain.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BackgroundModule {
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    @Singleton
    fun provideWorkerFactory(
        userPreferencesRepository: UserPreferencesRepository
    ): CustomWorkerFactory {
        return CustomWorkerFactory(userPreferencesRepository)
    }
}