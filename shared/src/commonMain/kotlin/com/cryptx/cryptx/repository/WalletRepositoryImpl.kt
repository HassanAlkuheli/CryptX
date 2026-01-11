package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction

/**
 * Minimal data provider object that returns static values from `WalletData`.
 * This file contains no business logic — it simply pulls and returns data.
 */
object WalletProvider {
    suspend fun getAddress(): String = WalletData.address

    suspend fun getWallet() = WalletData.wallet

    suspend fun getPriceHistory(symbol: String): PriceHistory =
        WalletData.priceHistories[symbol] ?: PriceHistory(symbol = symbol, candles = emptyList())

    suspend fun sendTransactionResult(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {
        val tx = WalletData.sampleTransaction.copy(
            to = to,
            amount = amount,
            symbol = symbol,
            id = "tx_${symbol}_sample"
        )
        return Result.success(tx)
    }
}
