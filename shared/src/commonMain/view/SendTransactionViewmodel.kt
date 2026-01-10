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

    private val _sendTransactionState = MutableStateFlow(SendTransactionState())
    val sendTransactionState: StateFlow<SendTransactionState> = _sendTransactionState

    private val scope = CoroutineScope(Dispatchers.Default)

    fun sendTransaction(to: String, amount: BigDecimal) {
        // First, validate address
        if (!validateAddressUseCase.execute(to)) {
            _sendTransactionState.value = SendTransactionState(error = "Invalid address")
            return
        }

        _sendTransactionState.value = SendTransactionState(isLoading = true, error = null)
        scope.launch {
            val result = sendTransactionUseCase.execute(to, amount)
            result.fold(
                onSuccess = { transaction ->
                    _sendTransactionState.value = SendTransactionState(transaction = transaction, isLoading = false)
                },
                onFailure = { exception ->
                    _sendTransactionState.value = SendTransactionState(isLoading = false, error = exception.message)
                }
            )
        }
    }
}