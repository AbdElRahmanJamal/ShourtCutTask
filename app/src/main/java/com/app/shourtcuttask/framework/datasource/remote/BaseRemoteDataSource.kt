package com.app.shourtcuttask.framework.datasource.remote


import com.app.shourtcuttask.domain.utils.Constant
import com.app.shourtcuttask.domain.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

abstract class BaseRemoteDataSource<ENTITY>(private val dispatcher: CoroutineDispatcher) {

    protected abstract suspend fun startRemoteApiCall(apiParameter: HashMap<String, Any>? = null): ENTITY

    protected suspend fun getResponseState(apiParameter: HashMap<String, Any>? = null): ResponseState<ENTITY> {
        return try {
            withTimeoutOrNull(Constant.NETWORK_READ_TIME_OUT) {
                val apiResponse = withContext(dispatcher) {
                    startRemoteApiCall(apiParameter)
                }
                onSuccess(apiResponse)
            } ?: onError(Constant.NETWORK_READ_TIME_OUT_MESSAGE)
        } catch (ex: Exception) {
            onError(Constant.GENERIC_ERROR_MESSAGE)
        }
    }

    protected abstract fun onSuccess(apiResponse: ENTITY): ResponseState<ENTITY>

    private fun onError(message: String): ResponseState<ENTITY> {
        return ResponseState.Error(message = message)
    }
}