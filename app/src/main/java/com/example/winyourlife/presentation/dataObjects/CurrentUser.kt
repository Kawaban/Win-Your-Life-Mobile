package com.example.winyourlife.presentation.dataObjects

import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.utils.Settings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrentUser @Inject constructor(val userPreferencesRepository: UserPreferencesRepository) {

    var userData: UserData? = null

    val mapOfSettings : MutableMap<String, String?> = mutableMapOf()

    fun updateUserData(email:String, name:String, avatar: ByteArray) {
        if (userData == null) {
            throw Exception("User data is null")
        }
        userData!!.email = email
        userData!!.name = name
        userData!!.avatar = avatar
    }

    fun resetUserData(){
        userData = null
    }

    suspend fun setSettings(){
        for (setting in Settings.entries) {
            val result = userPreferencesRepository.getParameter(setting.name).getOrNull()
            this.mapOfSettings[setting.name] = result

            if (setting.name == Settings.IS_DAILY_REMINDER.name && result == null)
                userPreferencesRepository.setParameter(setting.name, "true")
                userPreferencesRepository.setParameter("isCompleted", "false")
        }
    }
}