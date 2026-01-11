package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.Wallet

interface WalletDataSource {
    suspend fun getAddress(): String
    suspend fun getWallet(): Wallet
    suspend fun getPriceHistory(symbol: String): PriceHistory
    suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction>
}