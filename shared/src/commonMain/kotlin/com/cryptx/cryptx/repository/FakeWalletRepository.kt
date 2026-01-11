package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

class FakeWalletRepository : WalletRepository {
    override suspend fun getAddress(): String = WalletData.wallet.address

    override suspend fun getWallet(): Wallet = WalletData.wallet

    override suspend fun getPriceHistory(symbol: String): PriceHistory = PriceData.getPriceHistory(symbol)

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {
        val tx = Transaction(
            id = "tx_${kotlin.random.Random.nextLong()}",
            from = WalletData.wallet.address,
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.SUCCESS
        )
        return Result.success(tx)
    }
}