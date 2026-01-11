package com.cryptx.cryptx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cryptx.cryptx.repository.NetworkRepositoryImpl
import com.cryptx.cryptx.repository.WalletRepositoryImpl
import com.cryptx.cryptx.repository.ProfileRepositoryImpl
import com.cryptx.cryptx.repository.SymbolPriceRepositoryImpl
import com.cryptx.cryptx.ui.components.BottomNavBar
import com.cryptx.cryptx.ui.components.NavItem
import com.cryptx.cryptx.ui.screens.*
import com.cryptx.cryptx.ui.theme.Background
import com.cryptx.cryptx.ui.theme.CryptoWalletTheme
import com.cryptx.cryptx.usecase.GetWalletUseCase
import com.cryptx.cryptx.usecase.GetProfileUseCase
import com.cryptx.cryptx.usecase.GetHoldingUseCase
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import com.cryptx.cryptx.viewmodel.WalletViewModel
import com.cryptx.cryptx.viewmodel.ProfileViewModel
import com.cryptx.cryptx.viewmodel.HoldingViewModel
import com.cryptx.cryptx.viewmodel.SendTransactionViewModel

enum class Screen {
    LANDING, DASHBOARD, TRANSACTION, PROFILE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize repositories from shared module
        val walletRepository = WalletRepositoryImpl()
        val networkRepository = NetworkRepositoryImpl()
        val profileRepository = ProfileRepositoryImpl()
        val symbolPriceRepository = SymbolPriceRepositoryImpl()

        // Initialize use cases
        val validateAddressUseCase = ValidateAddressUseCase()
        val getWalletUseCase = GetWalletUseCase(walletRepository)
        val getProfileUseCase = GetProfileUseCase(profileRepository)
        val getHoldingUseCase = GetHoldingUseCase(walletRepository, symbolPriceRepository)
        val sendTransactionUseCase = SendTransactionUseCase(walletRepository, networkRepository, validateAddressUseCase)

        // Create ViewModels from shared module
        val walletViewModel = WalletViewModel(getWalletUseCase)
        val profileViewModel = ProfileViewModel(getProfileUseCase)
        val holdingViewModel = HoldingViewModel(getHoldingUseCase)
        val sendViewModel = SendTransactionViewModel(sendTransactionUseCase, validateAddressUseCase)

        setContent {
            CryptoWalletTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(walletViewModel, profileViewModel, sendViewModel)
                }
            }
        }

        // Load wallet and profile on start
        walletViewModel.loadWallet()
        profileViewModel.loadProfile()
    }
}

@Composable
fun AppNavigation(
    walletViewModel: WalletViewModel,
    profileViewModel: ProfileViewModel,
    sendViewModel: SendTransactionViewModel
) {
    val currentScreen = remember { mutableStateOf(Screen.LANDING) }
    val showBottomNav = currentScreen.value != Screen.LANDING

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Main content area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentScreen.value) {
                    Screen.LANDING -> {
                        LandingScreen(
                            onGetStarted = { currentScreen.value = Screen.DASHBOARD }
                        )
                    }

                    Screen.DASHBOARD -> {
                        DashboardScreen(
                            viewModel = walletViewModel,
                            onProfileClick = { currentScreen.value = Screen.PROFILE },
                            onSendClick = { currentScreen.value = Screen.TRANSACTION }
                        )
                    }

                    Screen.TRANSACTION -> {
                        TransactionScreen(
                            viewModel = sendViewModel,
                            onBackClick = { currentScreen.value = Screen.DASHBOARD }
                        )
                    }

                    Screen.PROFILE -> {
                        ProfileScreen(
                            viewModel = profileViewModel,
                            onBackClick = { currentScreen.value = Screen.DASHBOARD }
                        )
                    }
                }
            }

            // Bottom Navigation Bar (hidden on landing)
            if (showBottomNav) {
                BottomNavBar(
                    selectedItem = when (currentScreen.value) {
                        Screen.DASHBOARD -> NavItem.HOME
                        Screen.TRANSACTION -> NavItem.TRANSACTION
                        Screen.PROFILE -> NavItem.PROFILE
                        else -> NavItem.HOME
                    },
                    onItemSelected = { navItem ->
                        currentScreen.value = when (navItem) {
                            NavItem.HOME -> Screen.DASHBOARD
                            NavItem.TRANSACTION -> Screen.TRANSACTION
                            NavItem.PROFILE -> Screen.PROFILE
                        }
                    }
                )
            }
        }
    }
}