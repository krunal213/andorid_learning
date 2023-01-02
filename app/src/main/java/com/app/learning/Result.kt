package com.app.learning

sealed class Result<out T>{

    object Loading : Result<Nothing>()

    data class Success<out T>(val data : T) : Result<T>()

    class Error(val exception: Exception) : Result<Nothing>()

    object Empty : Result<Nothing>()

}



