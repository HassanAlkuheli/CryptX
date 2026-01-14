package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.state.AuthState
import com.cryptx.cryptx.usecase.SignInUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class AuthViewModel(private val signInUseCase: SignInUseCase) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun signIn(email: String, password: String, useBiometric: Boolean) {
        Log.d("AuthViewModel", "signIn called with email=$email, useBiometric=$useBiometric")
        scope.launch {
            try {
                Log.d("AuthViewModel", "signIn coroutine started")
                val profile = signInUseCase.signIn(email, password, useBiometric)
                Log.d("AuthViewModel", "signIn completed, profile email=${profile.email}")
                _state.value = AuthState(
                    email = profile.email,
                    password = profile.password,
                    biometricEnabled = profile.biometricLoginEnabled
                )
                Log.d("AuthViewModel", "state updated")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "signIn error", e)
            }
        }
    }
}
