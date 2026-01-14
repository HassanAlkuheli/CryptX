package com.cryptx.cryptx.repository

interface BiometricRepository {
    /**
     * Prompts the platform biometric UI and returns a biometric result string on success,
     * or null if authentication failed or was canceled.
     */
    suspend fun promptBiometric(): String?
}
