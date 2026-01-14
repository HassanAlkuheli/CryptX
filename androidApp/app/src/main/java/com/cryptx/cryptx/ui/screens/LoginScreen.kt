package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.cryptx.cryptx.state.AuthState
import com.cryptx.cryptx.usecase.SignInUseCase
import com.cryptx.cryptx.viewmodel.AuthViewModel
import com.cryptx.cryptx.repository.AndroidBiometricRepository
import com.cryptx.cryptx.repository.ProfileRepositoryImpl

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToProfile: () -> Unit = onLoginSuccess
) {
    val context = LocalContext.current
    val activity = context as FragmentActivity

    // create Android biometric repo and wire common ViewModel
    val authViewModel = remember {
        val profileRepository = ProfileRepositoryImpl()
        val biometricRepo = AndroidBiometricRepository { activity }
        val useCase = SignInUseCase(profileRepository, biometricRepo)
        AuthViewModel(useCase)
    }

    val authState by authViewModel.state.collectAsState(initial = AuthState())

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isSignUp by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.Center) {
        Text(text = if (isSignUp) "Sign Up" else "Login", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())

        if (isSignUp) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") }, modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            // For both sign-in and sign-up we use same useCase; biometric optional
            authViewModel.signIn(email.trim(), password, useBiometric = false)
            errorMessage = ""
        }) {
            Text(text = if (isSignUp) "Create account" else "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Validate credentials before biometric
                if (email.trim().isEmpty() || password.isEmpty()) {
                    errorMessage = "Please enter email and password first"
                } else {
                    // Trigger biometric flow; pass current credentials and request biometric
                    authViewModel.signIn(email.trim(), password, useBiometric = true)
                    errorMessage = ""
                }
            },
            enabled = email.trim().isNotEmpty() && password.isNotEmpty()
        ) {
            Text(text = "Use Biometric")
        }

        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { isSignUp = !isSignUp }) {
            Text(text = if (isSignUp) "Have an account? Login" else "Create an account")
        }

        // Observe auth state and navigate on successful biometric/login
        LaunchedEffect(authState.email) {
            if (authState.email.isNotBlank()) {
                // simple success criteria: we have an email from usecase
                onLoginSuccess()
            }
        }
    }
}
