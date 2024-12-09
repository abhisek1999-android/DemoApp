package com.example.demoapp.web

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String,val statusCode : Int? = null , ) : ApiResult<Nothing>()
}