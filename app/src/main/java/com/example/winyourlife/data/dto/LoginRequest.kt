package com.example.winyourlife.data.dto

data class LoginRequest(val email: String, val password: String) {
    fun isValid(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}