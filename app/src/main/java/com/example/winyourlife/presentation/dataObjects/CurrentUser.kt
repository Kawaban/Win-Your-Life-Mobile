package com.example.winyourlife.presentation.dataObjects

import com.example.winyourlife.data.dto.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUser @Inject constructor(){
    var userData: UserData? = null
    fun updateUserData(email:String, name:String, avatar: ByteArray){
        if(userData == null){
            throw Exception("User data is null")
        }
        userData!!.email = email
        userData!!.name = name
        userData!!.avatar = avatar
    }
    fun resetUserData(){
        userData = null
    }
}