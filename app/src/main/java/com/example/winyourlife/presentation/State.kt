package com.example.winyourlife.presentation

import com.example.winyourlife.domain.Resource
import kotlin.reflect.KProperty

data class State<T>(
    val obj: Resource<T>? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isReady: Boolean = false
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): State<T> = this

}
