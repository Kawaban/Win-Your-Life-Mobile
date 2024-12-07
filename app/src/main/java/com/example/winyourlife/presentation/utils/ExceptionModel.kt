package com.example.winyourlife.presentation.utils

import android.content.Context
import com.example.winyourlife.R

enum class ExceptionText(val text: String){
    Net("No internet connection"),
    ServerIsDown("Server is down"),
    InternalServer("Server error"),
    Unknown("Unknown error"),
    BadCredentials("Bad credentials"),
    UserAlreadyExists("User already exists"),
    BadInput("Bad input"),
    PasswordsDoNotMatch("Passwords do not match"),
    PasswordMustContain("Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one number"),
    TaskAlreadyExists("Task already exists")
}


fun mapExceptionText(error: String, context: Context):CharSequence{
    return when(error){
        ExceptionText.Net.text -> {
            context.getText(R.string.exception_network)
        }
        ExceptionText.ServerIsDown.text -> {
            context.getText(R.string.exception_server)
        }
        ExceptionText.InternalServer.text -> {
            context.getText(R.string.exception_internal_server)
        }
        ExceptionText.Unknown.text -> {
            context.getText(R.string.exception_unknown)
        }
        ExceptionText.BadCredentials.text -> {
            context.getText(R.string.bad_credentials)
        }
        ExceptionText.UserAlreadyExists.text -> {
            context.getText(R.string.exception_user_already_exists)
        }
        ExceptionText.BadInput.text -> {
            context.getText(R.string.exception_bad_input)
        }
        ExceptionText.PasswordsDoNotMatch.text -> {
            context.getText(R.string.exception_passwords_not_match)
        }
        ExceptionText.PasswordMustContain.text -> {
            context.getText(R.string.exception_password_is_not_valid)
        }
        ExceptionText.TaskAlreadyExists.text -> {
            context.getText(R.string.exception_task_already_exists)
        }
        else -> {
            context.getText(R.string.exception_unknown)
        }
    }
}