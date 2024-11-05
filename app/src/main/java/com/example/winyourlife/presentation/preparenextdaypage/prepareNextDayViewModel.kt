package com.example.winyourlife.presentation.preparenextdaypage

import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.utils.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrepareNextDayViewModel @Inject constructor() : ViewModel(), ViewModelCustomInterface {
    override fun resetViewModel() {

    }

}
