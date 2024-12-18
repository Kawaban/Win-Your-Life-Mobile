package com.example.winyourlife.presentation.utils

import com.example.winyourlife.domain.wrapper.Resource
import kotlin.reflect.KProperty

data class State<T>(
    val obj: Resource<T>? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isReady: Boolean = false
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): State<T> = this
}
