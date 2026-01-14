package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Profile
import com.cryptx.cryptx.repository.BiometricRepository
import com.cryptx.cryptx.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class SignInUseCase(
    private val profileRepository: ProfileRepository,
    private val biometricRepository: BiometricRepository
) {

    suspend fun signIn(email: String, password: String, useBiometric: Boolean): Profile = withContext(Dispatchers.Default) {
        Log.d("SignInUseCase", "signIn called: email=$email, useBiometric=$useBiometric")
        
        val rawBiometric = if (useBiometric) {
            Log.d("SignInUseCase", "Requesting biometric prompt...")
            withContext(Dispatchers.Main) {
                val result = biometricRepository.promptBiometric()
                Log.d("SignInUseCase", "Biometric prompt result: $result")
                result
            }
        } else {
            Log.d("SignInUseCase", "Skipping biometric, using credentials only")
            null
        }

        val normalized = rawBiometric?.trim()?.uppercase()

        val profile = Profile(
            email = email,
            password = password,
            biometricResult = normalized,
            biometricLoginEnabled = useBiometric && !normalized.isNullOrEmpty()
        )
        
        Log.d("SignInUseCase", "SignIn complete: profile.email=${profile.email}, biometricEnabled=${profile.biometricLoginEnabled}")

        // Save the profile
        // Assuming ProfileRepository has a saveProfile method, but it doesn't.
        // For now, just return the profile
        profile
    }
}
