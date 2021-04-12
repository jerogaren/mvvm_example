package com.jerogaren.characterslistmarvelmvvm.util

import java.lang.Exception

sealed class ResultApp<out T> {
    data class Success<out T>(val success : T) : ResultApp<T>()
    class Error(val exception: Exception, val message: String = exception.localizedMessage)
        :ResultApp<Nothing>()
}