package com.cryptx.cryptx.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.cryptx.cryptx.state.WalletState
import com.cryptx.cryptx.usecase.GetWalletUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

actual class WalletViewModel actual constructor(private val getWalletUseCase: GetWalletUseCase) : ViewModel() {

    private val _walletState = MutableStateFlow(WalletState())
    actual val walletState: StateFlow<WalletState> = _walletState

    actual fun loadWallet() {
        _walletState.value = _walletState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val wallet = getWalletUseCase.execute()
                _walletState.value = WalletState(wallet = wallet, isLoading = false)
            } catch (e: Exception) {
                _walletState.value = _walletState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    actual fun refresh() {
        loadWallet()
    }

    companion object {
        fun Factory(getWalletUseCase: GetWalletUseCase): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WalletViewModel(getWalletUseCase) as T
            }
        }
    }
}
