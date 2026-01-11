package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.PriceCandle
import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.TransactionStatus

/** Fake wallet repo implementing WalletRepository. Returns deterministic mock data (no logic). */
class FakeWalletRepository : WalletRepository {

    private val address = "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P"

    private val balances = listOf(
        // totalValue: 50.0
        BalanceItem(symbol = "BTC", name = "Bitcoin", quantity = 1.0, price = 50.0, totalValue = 50.0),
        // totalValue: 50.0
        BalanceItem(symbol = "ETH", name = "Ethereum", quantity = 1.0, price = 50.0, totalValue = 50.0)
    )

    override suspend fun getAddress(): String = address

    override suspend fun getWallet(): Wallet = Wallet(address = address, balances = balances)

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        val candles = (0 until 5).map { i ->
            PriceCandle(
                timestamp = i.toLong(),
                open = 40.0 + i,
                high = 45.0 + i,
                low = 39.0 + i,
                close = 42.0 + i,
                change = 0.5
            )
        }
        return PriceHistory(symbol = symbol, candles = candles)
    }

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {
        val tx = Transaction(
            id = "tx-${System.currentTimeMillis()}",
            from = address,
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.SUCCESS
        )
        return Result.success(tx)
    }
}
