package com.cryptx.cryptx.repository

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidBiometricRepository(private val activityProvider: () -> FragmentActivity) : BiometricRepository {

    override suspend fun promptBiometric(): String? = suspendCancellableCoroutine { cont ->
        val activity = activityProvider()
        val executor = ContextCompat.getMainExecutor(activity)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                cont.resume("AUTH_SUCCESS")
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                cont.resume(null)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // keep waiting; do not resume here because user may try again
            }
        }

        val prompt = BiometricPrompt(activity, executor, callback)

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric authentication")
            .setSubtitle("Authenticate to continue")
            .setNegativeButtonText("Cancel")
            .build()

        prompt.authenticate(promptInfo)

        cont.invokeOnCancellation {
            // nothing to clean up explicitly
        }
    }
}
