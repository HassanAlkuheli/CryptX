package com.cryptx.cryptx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cryptx.cryptx.repository.FakeNetworkRepository
import com.cryptx.cryptx.repository.FakeWalletRepository
import com.cryptx.cryptx.ui.screens.*
import com.cryptx.cryptx.ui.theme.CryptoWalletTheme
import com.cryptx.cryptx.usecase.GetWalletUseCase
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import com.cryptx.cryptx.view.SendTransactionViewModel
import com.cryptx.cryptx.view.WalletViewModel

enum class Screen {
    LANDING, DASHBOARD, SEND, PROFILE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize repositories and use cases
        val walletRepository = FakeWalletRepository()
        val networkRepository = FakeNetworkRepository()
        val validateAddressUseCase = ValidateAddressUseCase()
        val getWalletUseCase = GetWalletUseCase(walletRepository)
        val sendTransactionUseCase = SendTransactionUseCase(walletRepository, networkRepository, validateAddressUseCase)

        // Create ViewModels
        val walletViewModel = WalletViewModel(getWalletUseCase)
        val sendViewModel = SendTransactionViewModel(sendTransactionUseCase, validateAddressUseCase)

        setContent {
            CryptoWalletTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(walletViewModel, sendViewModel)
                }
            }
        }

        // Load wallet on start
        walletViewModel.loadWallet()
    }
}

@Composable
fun AppNavigation(
    walletViewModel: WalletViewModel,
    sendViewModel: SendTransactionViewModel
) {
    val currentScreen = remember { mutableStateOf(Screen.LANDING) }

    when (currentScreen.value) {
        Screen.LANDING -> {
            LandingScreen(
                onGetStarted = { currentScreen.value = Screen.DASHBOARD }
            )
        }

        Screen.DASHBOARD -> {
            DashboardScreen(
                viewModel = walletViewModel,
                onProfileClick = { currentScreen.value = Screen.PROFILE }
            )
        }

        Screen.SEND -> {
            SendScreen(
                viewModel = sendViewModel,
                onBackClick = { currentScreen.value = Screen.DASHBOARD }
            )
        }

        Screen.PROFILE -> {
            ProfileScreen(
                onBackClick = { currentScreen.value = Screen.DASHBOARD }
            )
        }
    }
}