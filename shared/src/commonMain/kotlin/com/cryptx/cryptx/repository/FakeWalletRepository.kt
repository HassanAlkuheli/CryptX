package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/**
 * Fake repository that simply pulls data from the mock data provider.
 * The repository contains no business logic — it just returns data.
 */
class FakeWalletRepository : WalletRepository {
    override suspend fun getAddress(): String {
        return WalletDataSource.getAddress()
    }

    override suspend fun getWallet(): Wallet {
        return WalletDataSource.getWallet()
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        return WalletDataSource.getPriceHistory(symbol)
    }

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {
        val tx = WalletDataSource.buildTransaction(id = "tx_mock_${symbol}_$amount", to = to, amount = amount, symbol = symbol)
        return Result.success(tx)
    }
}