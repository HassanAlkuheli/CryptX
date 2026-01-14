package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.Wallet

class FakeWalletRepository : WalletRepository {

    override suspend fun getAddress(): String = "fake_address"

    override suspend fun getWallet(): Wallet = Wallet(
        address = "fake_address",
        balances = listOf(
            BalanceItem("BTC", "Bitcoin", 1.0, 50000.0),
            BalanceItem("ETH", "Ethereum", 10.0, 3000.0)
        )
    )

    override suspend fun getBalances(): List<BalanceItem> = listOf(
        BalanceItem("BTC", "Bitcoin", 1.0, 50000.0),
        BalanceItem("ETH", "Ethereum", 10.0, 3000.0)
    )

    override suspend fun getBalanceBySymbol(symbol: String): BalanceItem? = when (symbol) {
        "BTC" -> BalanceItem("BTC", "Bitcoin", 1.0, 50000.0)
        "ETH" -> BalanceItem("ETH", "Ethereum", 10.0, 3000.0)
        else -> null
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory = PriceHistory(
        symbol = symbol,
        candles = emptyList()
    )

    override suspend fun sendTransaction(to: String, amount: Double, symbol: String): Result<Transaction> = Result.success(
        Transaction(
            id = "fake_tx",
            from = "fake_address",
            to = to,
            amount = amount,
            symbol = symbol,
            status = com.cryptx.cryptx.domain.TransactionStatus.SUCCESS
        )
    )
}