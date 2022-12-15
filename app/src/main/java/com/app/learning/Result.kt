package com.app.learning

import java.lang.Exception

sealed class Result<out T>{

    object Loading : Result<Nothing>()

    data class Success<out T>(val data : T) : Result<T>()

    class Error(exception: Exception) : Result<Nothing>()

}



