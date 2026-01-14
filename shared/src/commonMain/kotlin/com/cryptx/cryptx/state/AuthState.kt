package com.cryptx.cryptx.state

data class AuthState(
    val email: String = "",
    val password: String = "",
    val biometricEnabled: Boolean = false
)
