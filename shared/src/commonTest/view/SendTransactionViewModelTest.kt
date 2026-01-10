package com.cryptx.cryptx.view

import com.cryptx.cryptx.repository.FakeNetworkRepository
import com.cryptx.cryptx.repository.FakeWalletRepository
import com.cryptx.cryptx.state.SendTransactionState
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class SendTransactionViewModelTest {

    private val walletRepository = FakeWalletRepository()
    private val networkRepository = FakeNetworkRepository()
    private val validateAddressUseCase = ValidateAddressUseCase()
    private val sendTransactionUseCase = SendTransactionUseCase(walletRepository, networkRepository, validateAddressUseCase)
    private val viewModel = SendTransactionViewModel(sendTransactionUseCase, validateAddressUseCase)

    @Test
    fun `sendTransaction with valid address and sufficient balance succeeds`() = runTest {
        viewModel.sendTransaction("0xValidAddress123", java.math.BigDecimal("10.0"))

        val state = viewModel.sendTransactionState.value
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", state.transaction?.from)
        assertEquals("0xValidAddress123", state.transaction?.to)
        assertEquals(java.math.BigDecimal("10.0"), state.transaction?.amount)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
    }

    @Test
    fun `sendTransaction with invalid address sets error`() = runTest {
        viewModel.sendTransaction("InvalidAddress", java.math.BigDecimal("10.0"))

        val state = viewModel.sendTransactionState.value
        assertEquals("Invalid address", state.error)
        assertFalse(state.isLoading)
        assertEquals(null, state.transaction)
    }

    @Test
    fun `sendTransaction with insufficient balance sets error from use case`() = runTest {
        viewModel.sendTransaction("0xValidAddress123", java.math.BigDecimal("200.0"))

        val state = viewModel.sendTransactionState.value
        assertEquals("Insufficient balance", state.error)
        assertFalse(state.isLoading)
        assertEquals(null, state.transaction)
    }
}