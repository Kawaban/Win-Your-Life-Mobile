package com.example.winyourlife.presentation.statisticspage

import androidx.lifecycle.ViewModel
import com.example.winyourlife.presentation.ViewModelCustomInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor() : ViewModel(), ViewModelCustomInterface {
    override fun resetViewModel() {

    }

}