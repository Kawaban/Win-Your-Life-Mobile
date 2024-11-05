package com.example.winyourlife.presentation.settingspage

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.Settings
import com.example.winyourlife.presentation.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(val userPreferencesRepository: UserPreferencesRepository, val currentUser: CurrentUser) : ViewModel(), ViewModelCustomInterface {


    override fun resetViewModel() {

    }


    fun saveSettings(name : String, value: String){
        viewModelScope.launch {
            userPreferencesRepository.setParameter(name, value)
        }
        currentUser.userData?.mapOfSettings?.set(name, value)
    }


}