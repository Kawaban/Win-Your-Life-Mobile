package com.example.winyourlife.data.exceptions

data class APIError(val status:String, val message: String, val statusCode:Int, val path:String) {
}
