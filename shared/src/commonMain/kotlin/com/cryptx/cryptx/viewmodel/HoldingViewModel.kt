package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.state.HoldingState
import com.cryptx.cryptx.usecase.GetHoldingUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HoldingViewModel(
    private val getHoldingUseCase: GetHoldingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private val _state = MutableStateFlow(HoldingState())
    val state: StateFlow<HoldingState> = _state

    private val scope = CoroutineScope(dispatcher)

    fun loadHolding(symbol: String) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        scope.launch {
            try {
                val holding = getHoldingUseCase.execute(symbol)
                _state.value = _state.value.copy(holding = holding, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
