package com.app.shourtcuttask.domain.utils

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Data<T>(data: T) : ResponseState<T>(data)
    class Error<T>(message: String) : ResponseState<T>(message = message)
}