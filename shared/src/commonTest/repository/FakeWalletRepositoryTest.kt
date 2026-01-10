package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.TransactionStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class FakeWalletRepositoryTest {

    private val repository = FakeWalletRepository()

    @Test
    fun `getAddress returns mock address`() = runTest {
        val address = repository.getAddress()
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", address)
    }

    @Test
    fun `getBalance returns mock balance`() = runTest {
        val balance = repository.getBalance()
        assertEquals(java.math.BigDecimal("100.0"), balance)
    }

    @Test
    fun `sendTransaction returns success with valid transaction`() = runTest {
        val result = repository.sendTransaction("0xToAddress", java.math.BigDecimal("10.0"))
        assertTrue(result.isSuccess)
        val transaction = result.getOrNull()!!
        assertEquals(repository.getAddress(), transaction.from)
        assertEquals("0xToAddress", transaction.to)
        assertEquals(java.math.BigDecimal("10.0"), transaction.amount)
        assertEquals(TransactionStatus.SUCCESS, transaction.status)
        assertTrue(transaction.id.startsWith("tx_"))
    }
}