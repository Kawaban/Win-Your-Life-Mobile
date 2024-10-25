package com.example.winyourlife.presentation

import com.example.winyourlife.domain.Resource

data class State<T>(
    val obj: Resource<T>? = null,
    val error: String? = null,
    val isReady: Boolean = false
)
