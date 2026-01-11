package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.domain.PriceHistory
import java.math.BigDecimal

interface WalletRepository {
    suspend fun getAddress(): String
    suspend fun getWallet(): Wallet
    suspend fun getPriceHistory(symbol: String): PriceHistory
    suspend fun sendTransaction(
        to: String,
        amount: BigDecimal,
        symbol: String
    ): Result<Transaction>
}