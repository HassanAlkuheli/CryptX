package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction

/**
 * Minimal repository implementation that returns static data from `WalletData`.
 * This file contains no business logic — it simply pulls and returns data.
 */
class WalletRepositoryImpl : WalletRepository {
    override suspend fun getAddress(): String = WalletData.address

    override suspend fun getWallet() = WalletData.wallet

    override suspend fun getPriceHistory(symbol: String): PriceHistory =
        WalletData.priceHistories[symbol] ?: PriceHistory(symbol = symbol, candles = emptyList())

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {
        // No business logic: always return the sample transaction with updated fields
        val tx = WalletData.sampleTransaction.copy(
            to = to,
            amount = amount,
            symbol = symbol,
            id = "tx_${symbol}_${System.currentTimeMillis()}"
        )
        return Result.success(tx)
    }
}
