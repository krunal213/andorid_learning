package com.app.learning.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.learning.InvalidPasswordException
import com.app.learning.InvalidUserDetailException
import com.app.learning.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LearningViewmodel : ViewModel() {

    fun openMainScreenAfterDelay() = flow<Result<*>> {
        delay(3000)
        emit(Result.Success(null))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )

    private val _validatePhoneNumberUserNameOrEmailAndPassword =
        MutableStateFlow<Result<*>>(Result.Empty)
    val isUserDetailValid: StateFlow<Result<*>> = _validatePhoneNumberUserNameOrEmailAndPassword

    fun validatePhoneNumberUserNameOrEmailAndPassword(
        phoneNumberUserNameOrEmail: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _validatePhoneNumberUserNameOrEmailAndPassword
                    .update {
                        Result.Loading
                    }
                if (phoneNumberUserNameOrEmail.isEmpty()) {
                    throw InvalidUserDetailException()
                }
                if (password.isEmpty()) {
                    throw InvalidPasswordException()
                }
                _validatePhoneNumberUserNameOrEmailAndPassword
                    .update {
                        Result.Success(null)
                    }
            } catch (ex: Exception) {
                _validatePhoneNumberUserNameOrEmailAndPassword
                    .update {
                        Result.Error(ex)
                    }
            }
        }
    }

}
