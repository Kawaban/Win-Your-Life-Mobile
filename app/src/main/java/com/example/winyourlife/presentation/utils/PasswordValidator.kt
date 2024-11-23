package com.example.winyourlife.presentation.utils


class PasswordValidator {
    fun validatePassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$".toRegex()
        return passwordPattern.matches(password)
    }

    fun checkPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}