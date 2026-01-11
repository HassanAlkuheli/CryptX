package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.domain.Holding
import com.cryptx.cryptx.state.WalletState
import com.cryptx.cryptx.usecase.GetWalletUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the wallet screen. Thin layer: calls usecase and maps to state.
 */
class WalletViewModel(
    private val getWalletUseCase: GetWalletUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private val _state = MutableStateFlow(WalletState())
    val state: StateFlow<WalletState> = _state

    private val scope = CoroutineScope(dispatcher)

    fun loadWallet() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        scope.launch {
            try {
                val wallet = getWalletUseCase.execute()
                val holdings = wallet.balances.map {
                    Holding(
                        symbol = it.symbol,
                        name = it.name,
                        amount = it.quantity,
                        currentPrice = it.price,
                        eqToUsdt = it.totalValue,
                        priceHistory = null
                    )
                }
                _state.value = WalletState(address = wallet.address, holdings = holdings, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun refresh() = loadWallet()
}
