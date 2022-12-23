package com.app.learning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.learning.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class LoginViewmodel : ViewModel() {
    fun openMainScreenAfterDelay() = flow<Result<*>> {
        delay(3000)
        emit(Result.Success(null))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )

}