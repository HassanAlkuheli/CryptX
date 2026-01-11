package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.state.SendTransactionState
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for send transaction flow.
 * Thin layer: calls use-cases and maps to state.
 */
class SendTransactionViewModel(
    private val sendTransactionUseCase: SendTransactionUseCase,
    private val validateAddressUseCase: ValidateAddressUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private val _state = MutableStateFlow(SendTransactionState())
    val state: StateFlow<SendTransactionState> = _state

    private val scope = CoroutineScope(dispatcher)

    /**
     * Validate the address format (starts with 0x, length, etc.)
     */
    fun validateAddress(address: String): Boolean {
        return validateAddressUseCase.execute(address)
    }

    /**
     * Send a transaction and update state accordingly.
     */
    fun send(to: String, amount: Double, symbol: String) {
        _state.value = SendTransactionState(isLoading = true, transaction = null, error = null)
        scope.launch {
            val result = sendTransactionUseCase.execute(to, amount, symbol)
            result.fold(
                onSuccess = { tx ->
                    _state.value = SendTransactionState(isLoading = false, transaction = tx, error = null)
                },
                onFailure = { e ->
                    _state.value = SendTransactionState(isLoading = false, transaction = null, error = e.message)
                }
            )
        }
    }

    /**
     * Reset state (e.g., after navigating away or retrying).
     */
    fun reset() {
        _state.value = SendTransactionState()
    }
}
