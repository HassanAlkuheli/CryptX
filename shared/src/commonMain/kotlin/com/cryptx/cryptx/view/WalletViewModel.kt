package com.cryptx.cryptx.view

import com.cryptx.cryptx.state.WalletState
import com.cryptx.cryptx.usecase.GetWalletUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel(private val getWalletUseCase: GetWalletUseCase) {

    private val _walletState = MutableStateFlow(WalletState())
    val walletState: StateFlow<WalletState> = _walletState

    private val scope = CoroutineScope(Dispatchers.Default)

    fun loadWallet() {
        _walletState.value = _walletState.value.copy(isLoading = true, error = null)
        scope.launch {
            try {
                val wallet = getWalletUseCase.execute()
                _walletState.value = WalletState(wallet = wallet, isLoading = false)
            } catch (e: Exception) {
                _walletState.value = _walletState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun refresh() {
        loadWallet()
    }
}