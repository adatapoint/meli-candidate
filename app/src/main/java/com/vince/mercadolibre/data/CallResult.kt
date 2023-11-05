package com.vince.mercadolibre.data

sealed class CallResult<out T> {
    data class Loading(var loading: Boolean) : CallResult<Nothing>()
    data class Success<out T>(val data: T) : CallResult<T>()
    data class Failure(val t: Throwable) : CallResult<Nothing>()

    companion object {
        fun <T> loading(isLoading: Boolean): CallResult<T> = Loading(isLoading)
        fun <T> success(data: T): CallResult<T> = Success(data)
        fun <T> failure(t: Throwable): CallResult<Nothing> = Failure(t)
    }
}
