package com.cryptx.cryptx.view

import com.cryptx.cryptx.state.SendTransactionState
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SendTransactionViewModel(
    private val sendTransactionUseCase: SendTransactionUseCase,
    private val validateAddressUseCase: ValidateAddressUseCase
) {

    private val _sendState = MutableStateFlow(SendTransactionState())
    val sendState: StateFlow<SendTransactionState> = _sendState

    private val scope = CoroutineScope(Dispatchers.Default)

    fun send(to: String, amount: BigDecimal, symbol: String = "BTC") {
        // First, validate address
        if (!validateAddressUseCase.execute(to)) {
            _sendState.value = SendTransactionState(error = "Invalid address")
            return
        }

        _sendState.value = SendTransactionState(isLoading = true, error = null)
        scope.launch {
            val result = sendTransactionUseCase.execute(to, amount, symbol)
            result.fold(
                onSuccess = { transaction ->
                    _sendState.value = SendTransactionState(transaction = transaction, isLoading = false)
                },
                onFailure = { exception ->
                    _sendState.value = SendTransactionState(isLoading = false, error = exception.message)
                }
            )
        }
    }
}