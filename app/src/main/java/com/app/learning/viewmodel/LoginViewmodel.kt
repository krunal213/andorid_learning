package com.app.learning.viewmodel

import androidx.lifecycle.ViewModel
import com.app.learning.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class LoginViewmodel : ViewModel() {
    fun openMainScreenAfterDelay() = flow<Result<*>>{
        emit(Result.Loading)
        delay(3000)
        emit(Result.Success(null))
    }
}