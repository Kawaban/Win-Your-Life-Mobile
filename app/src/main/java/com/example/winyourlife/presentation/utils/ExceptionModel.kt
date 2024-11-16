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
        else -> {
            context.getText(R.string.exception_unknown)
        }
    }
}