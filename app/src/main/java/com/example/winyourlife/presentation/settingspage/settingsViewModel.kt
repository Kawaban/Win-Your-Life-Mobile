package com.example.winyourlife.presentation.settingspage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(val userPreferencesRepository: UserPreferencesRepository, val currentUser: CurrentUser, val jwtManager: JwtManager) : ViewModel(),
    ViewModelCustomInterface {

    override fun resetViewModel() {
    }

    fun saveSettings(name : String, value: String){
        viewModelScope.launch {
            userPreferencesRepository.setParameter(name, value)
        }
        currentUser.mapOfSettings[name] = value
    }

    fun logOut(){
        viewModelScope.launch{
            currentUser.resetUserData()
            jwtManager.resetJwt()
        }
    }
}