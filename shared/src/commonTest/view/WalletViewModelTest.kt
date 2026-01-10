package com.cryptx.cryptx.view

import com.cryptx.cryptx.repository.FakeWalletRepository
import com.cryptx.cryptx.state.WalletState
import com.cryptx.cryptx.usecase.GetWalletUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class WalletViewModelTest {

    private val walletRepository = FakeWalletRepository()
    private val getWalletUseCase = GetWalletUseCase(walletRepository)
    private val viewModel = WalletViewModel(getWalletUseCase)

    @Test
    fun `loadWallet updates state with wallet data`() = runTest {
        viewModel.loadWallet()

        val state = viewModel.walletState.value
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", state.wallet?.address)
        assertEquals(java.math.BigDecimal("100.0"), state.wallet?.balance)
        assertFalse(state.isLoading)
        assertTrue(state.transactions.isEmpty())
        assertEquals(null, state.error)
    }

    @Test
    fun `refresh calls loadWallet again`() = runTest {
        viewModel.refresh()

        val state = viewModel.walletState.value
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", state.wallet?.address)
    }
}