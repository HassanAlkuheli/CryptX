package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.repository.FakeNetworkRepository
import com.cryptx.cryptx.repository.FakeWalletRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class SendTransactionUseCaseTest {

    private val walletRepository = FakeWalletRepository()
    private val networkRepository = FakeNetworkRepository()
    private val validateAddressUseCase = ValidateAddressUseCase()
    private val useCase = SendTransactionUseCase(walletRepository, networkRepository, validateAddressUseCase)

    @Test
    fun `execute returns success for valid transaction`() = runTest {
        val result = useCase.execute("0xValidAddress123", 10.0)
        assertTrue(result.isSuccess)
        val transaction = result.getOrNull()!!
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", transaction.from)
        assertEquals("0xValidAddress123", transaction.to)
        assertEquals(10.0, transaction.amount)
    }

    @Test
    fun `execute returns failure for invalid address`() = runTest {
        val result = useCase.execute("InvalidAddress", 10.0)
        assertTrue(result.isFailure)
        assertEquals("Invalid address", result.exceptionOrNull()?.message)
    }

    @Test
    fun `execute returns failure for insufficient balance`() = runTest {
        val result = useCase.execute("0xValidAddress123", 200.0)
        assertTrue(result.isFailure)
        assertEquals("Insufficient balance for BTC", result.exceptionOrNull()?.message)
    }

    // Note: Network is always available in FakeNetworkRepository, so no test for network unavailable
    // To test network failure, would need a mock that can be configured
}