package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.repository.WalletRepositoryImpl
import com.cryptx.cryptx.usecase.GetWalletUseCase
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WalletViewModelTest {

    @Test
    fun loadWallet_populatesState() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val usecase = GetWalletUseCase(WalletRepositoryImpl())
        val vm = WalletViewModel(usecase, dispatcher)

        vm.loadWallet()
        // let coroutines run
        testScheduler.advanceUntilIdle()

        val state = vm.state.value
        assertTrue(state.holdings.isNotEmpty())
        assertTrue(state.address.startsWith("0x"))
    }
}
