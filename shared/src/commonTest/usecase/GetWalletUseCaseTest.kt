package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.repository.FakeWalletRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class GetWalletUseCaseTest {

    private val walletRepository = FakeWalletRepository()
    private val useCase = GetWalletUseCase(walletRepository)

    @Test
    fun `execute returns wallet with correct address and balance`() = runTest {
        val wallet = useCase.execute()
        assertEquals("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", wallet.address)
        assertEquals(java.math.BigDecimal("100.0"), wallet.balance)
    }
}