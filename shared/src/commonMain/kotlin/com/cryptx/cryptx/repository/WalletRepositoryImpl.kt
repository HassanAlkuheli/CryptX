package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.Wallet

/** Thin repository implementation that delegates to a data source and contains no business logic. */
class WalletRepositoryImpl(
    private val dataSource: WalletDataSource
) : WalletRepository {
    override suspend fun getAddress(): String = dataSource.getAddress()

    override suspend fun getWallet(): Wallet = dataSource.getWallet()

    override suspend fun getPriceHistory(symbol: String): PriceHistory = dataSource.getPriceHistory(symbol)

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> = dataSource.sendTransaction(to, amount, symbol)
}