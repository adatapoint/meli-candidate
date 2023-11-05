package com.vince.mercadolibre.utils

import com.vince.mercadolibre.data.CallResult

inline fun <T, R> retrofit2.Response<T>.handleResponse(
    transform: (data: T) -> R
): CallResult<R> {
    return try {
        if (isSuccessful) {
            val body = body()
            if (body != null) {
                CallResult.Success(transform(body))
            } else {
                CallResult.Failure(Throwable(errorBody()?.string() ?: "Response body is null"))
            }
        } else {
            val errorBody = errorBody()?.string()
            CallResult.Failure(Throwable(errorBody ?: "Unknown error"))
        }
    } catch (e: Exception) {
        CallResult.Failure(Throwable(e.localizedMessage ?: "Unknown error"))
    }

    // TODO
}
