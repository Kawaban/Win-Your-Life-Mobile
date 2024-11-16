package com.example.winyourlife.presentation.dataObjects

import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.utils.Settings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUser @Inject constructor(val userPreferencesRepository: UserPreferencesRepository){
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
        for(setting in Settings.entries){
            this.mapOfSettings[setting.name] = userPreferencesRepository.getParameter(setting.name).getOrNull()
        }
    }
}