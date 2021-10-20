package com.app.shourtcuttask.domain.utils

sealed class UiState<out T : Any> {
    object LoadingState : UiState<Nothing>()
    class DataState<T : Any>(val value: T) : UiState<T>()
    class ErrorState(val message: String) : UiState<Nothing>()
}