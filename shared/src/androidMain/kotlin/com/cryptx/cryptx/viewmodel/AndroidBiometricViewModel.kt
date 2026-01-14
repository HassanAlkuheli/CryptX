package com.cryptx.cryptx.viewmodel

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class AndroidBiometricViewModel(private val activityProvider: () -> FragmentActivity) : ViewModel() {

    private val _authResult = MutableStateFlow<String?>(null)
    val authResult: StateFlow<String?> = _authResult

    fun resetAuthResult() {
        Log.d("AndroidBiometricViewModel", "resetAuthResult called")
        _authResult.value = null
    }

    fun initiateBiometric() {
        Log.d("AndroidBiometricViewModel", "initiateBiometric called")
        try {
            val activity = activityProvider()
            val executor = ContextCompat.getMainExecutor(activity)

            val callback = object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.d("AndroidBiometricViewModel", "onAuthenticationSucceeded")
                    viewModelScope.launch {
                        _authResult.value = "AUTHORIZED"
                    }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d("AndroidBiometricViewModel", "onAuthenticationError: $errorCode - $errString")
                    viewModelScope.launch {
                        _authResult.value = "FAILED"
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.d("AndroidBiometricViewModel", "onAuthenticationFailed")
                    // keep waiting
                }
            }

            val prompt = BiometricPrompt(activity, executor, callback)

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric authentication")
                .setSubtitle("Authenticate to enable biometric login")
                .setNegativeButtonText("Cancel")
                .build()

            Log.d("AndroidBiometricViewModel", "Calling prompt.authenticate()")
            prompt.authenticate(promptInfo)
            Log.d("AndroidBiometricViewModel", "prompt.authenticate() returned")
        } catch (e: Exception) {
            Log.e("AndroidBiometricViewModel", "Error in initiateBiometric", e)
        }
    }
}