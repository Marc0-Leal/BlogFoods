package com.evalenzuela.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evalenzuela.navigation.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Unauthenticated : AuthState()
    object Authenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val normalizedEmail = email.trim().lowercase()
            val normalizedPassword = password.trim()

            val res = repo.login(normalizedEmail, normalizedPassword)

            _authState.value = if (res.isSuccess) {
                AuthState.Authenticated
            } else {
                val message = res.exceptionOrNull()?.message ?: "Credenciales inválidas"
                AuthState.Error(message)
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val res = repo.register(email.trim(), password)
            _authState.value = if (res.isSuccess) AuthState.Authenticated
            else AuthState.Error(res.exceptionOrNull()?.message ?: "Error")
        }
    }

    fun logout() {
        _authState.value = AuthState.Unauthenticated
    }

    fun resetError() {
        if (_authState.value is AuthState.Error) _authState.value = AuthState.Unauthenticated
    }
}
