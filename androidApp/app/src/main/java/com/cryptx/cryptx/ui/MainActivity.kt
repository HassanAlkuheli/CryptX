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
import com.cryptx.cryptx.viewmodel.NavBarViewModel
import com.cryptx.cryptx.state.NavBarState
import com.cryptx.cryptx.state.NavScreen
import androidx.compose.runtime.collectAsState

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
        // Nav bar ViewModel (shared)
        val navViewModel = NavBarViewModel()

        setContent {
            CryptoWalletTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(walletViewModel, profileViewModel, sendViewModel, navViewModel)
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
    sendViewModel: SendTransactionViewModel,
    navViewModel: NavBarViewModel
) {
    val currentScreen = remember { mutableStateOf(Screen.LANDING) }
    val showBottomNav = currentScreen.value != Screen.LANDING

    // Observe nav selection from shared ViewModel
    val navState = navViewModel.state.collectAsState(initial = NavBarState())

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
                            onGetStarted = {
                                currentScreen.value = Screen.DASHBOARD
                                navViewModel.select(NavScreen.HOME)
                            }
                        )
                    }

                    Screen.DASHBOARD -> {
                        DashboardScreen(
                            viewModel = walletViewModel,
                            onProfileClick = {
                                currentScreen.value = Screen.PROFILE
                                navViewModel.select(NavScreen.PROFILE)
                            },
                            onSendClick = {
                                currentScreen.value = Screen.TRANSACTION
                                navViewModel.select(NavScreen.TRANSACTION)
                            }
                        )
                    }

                    Screen.TRANSACTION -> {
                        TransactionScreen(
                            viewModel = sendViewModel,
                            onBackClick = {
                                currentScreen.value = Screen.DASHBOARD
                                navViewModel.select(NavScreen.HOME)
                            }
                        )
                    }

                    Screen.PROFILE -> {
                        ProfileScreen(
                            viewModel = profileViewModel,
                            onBackClick = {
                                currentScreen.value = Screen.DASHBOARD
                                navViewModel.select(NavScreen.HOME)
                            }
                        )
                    }
                }
            }

            // Bottom Navigation Bar (hidden on landing)
            if (showBottomNav) {
                BottomNavBar(
                    selectedItem = when (navState.value.selected) {
                        NavScreen.HOME -> NavItem.HOME
                        NavScreen.TRANSACTION -> NavItem.TRANSACTION
                        NavScreen.PROFILE -> NavItem.PROFILE
                    },
                    onItemSelected = { navItem ->
                        when (navItem) {
                            NavItem.HOME -> {
                                currentScreen.value = Screen.DASHBOARD
                                navViewModel.select(NavScreen.HOME)
                            }
                            NavItem.TRANSACTION -> {
                                currentScreen.value = Screen.TRANSACTION
                                navViewModel.select(NavScreen.TRANSACTION)
                            }
                            NavItem.PROFILE -> {
                                currentScreen.value = Screen.PROFILE
                                navViewModel.select(NavScreen.PROFILE)
                            }
                        }
                    }
                )
            }
        }
    }
}