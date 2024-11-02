package com.example.winyourlife.presentation.dataObjects

import com.example.winyourlife.data.dto.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUser @Inject constructor(){
    var userData: UserResponse? = null
    fun setUserData(userData: UserResponse){
        this.userData = userData
    }
    fun getUserData(): UserResponse? {
        return userData
    }
}