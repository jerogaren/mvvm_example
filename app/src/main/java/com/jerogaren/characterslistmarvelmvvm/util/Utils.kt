package com.jerogaren.characterslistmarvelmvvm.util

import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): ResultApp.Error {
        val error = ApiErrorUtils.parseError(resp)
        return ResultApp.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): ResultApp<T> {
        response.body()?.let {
            return ResultApp.Success(it)
        } ?: return handleApiError(response)
    }
}