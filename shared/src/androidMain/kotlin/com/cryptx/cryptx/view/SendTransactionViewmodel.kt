package com.cryptx.cryptx.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.cryptx.cryptx.state.SendTransactionState
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

actual class SendTransactionViewModel actual constructor(
    private val sendTransactionUseCase: SendTransactionUseCase,
    private val validateAddressUseCase: ValidateAddressUseCase
) : ViewModel() {

    private val _sendState = MutableStateFlow(SendTransactionState())
    actual val sendState: StateFlow<SendTransactionState> = _sendState

    actual fun send(to: String, amount: BigDecimal, symbol: String = "BTC") {
        // First, validate address
        if (!validateAddressUseCase.execute(to)) {
            _sendState.value = SendTransactionState(error = "Invalid address")
            return
        }

        _sendState.value = SendTransactionState(isLoading = true, error = null)
        viewModelScope.launch {
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

    companion object {
        fun Factory(
            sendTransactionUseCase: SendTransactionUseCase,
            validateAddressUseCase: ValidateAddressUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SendTransactionViewModel(sendTransactionUseCase, validateAddressUseCase) as T
            }
        }
    }
}
