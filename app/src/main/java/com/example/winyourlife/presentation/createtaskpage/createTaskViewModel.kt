package com.example.winyourlife.presentation.createtaskpage

import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor() : ViewModel(), ViewModelCustomInterface {
    override fun resetViewModel() {

    }

}