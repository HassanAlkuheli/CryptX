package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.repository.SymbolPriceRepositoryImpl
import com.cryptx.cryptx.repository.WalletRepositoryImpl
import com.cryptx.cryptx.usecase.GetHoldingUseCase
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HoldingViewModelTest {

    @Test
    fun loadHolding_existingSymbol_setsHolding() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val usecase = GetHoldingUseCase(WalletRepositoryImpl(), SymbolPriceRepositoryImpl())
        val vm = HoldingViewModel(usecase, dispatcher)

        vm.loadHolding("BTC")
        testScheduler.advanceUntilIdle()

        val state = vm.state.value
        assertNotNull(state.holding)
        assertEquals("BTC", state.holding?.symbol)
    }

    @Test
    fun loadHolding_missingSymbol_setsNull() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val usecase = GetHoldingUseCase(WalletRepositoryImpl(), SymbolPriceRepositoryImpl())
        val vm = HoldingViewModel(usecase, dispatcher)

        vm.loadHolding("UNKNOWN")
        testScheduler.advanceUntilIdle()

        val state = vm.state.value
        assertEquals(null, state.holding)
    }
}
