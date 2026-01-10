package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*
import java.math.BigDecimal

class FakeWalletRepository : WalletRepository {
    private val mockWallet = Wallet(
        address = "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P",
        balances = listOf(
            BalanceItem(
                symbol = "BTC",
                name = "Bitcoin",
                quantity = BigDecimal("0.08"),
                price = BigDecimal("26927.00")
            ),
            BalanceItem(
                symbol = "ETH",
                name = "Ethereum",
                quantity = BigDecimal("2.5"),
                price = BigDecimal("2012.48")
            ),
            BalanceItem(
                symbol = "LTC",
                name = "Litecoin",
                quantity = BigDecimal("5.0"),
                price = BigDecimal("1385.40")
            ),
            BalanceItem(
                symbol = "XRP",
                name = "Ripple",
                quantity = BigDecimal("1000.0"),
                price = BigDecimal("4.637")
            )
        )
    )

    override suspend fun getAddress(): String {
        return mockWallet.address
    }

    override suspend fun getWallet(): Wallet {
        return mockWallet
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        val candles = generateMockCandles(symbol)
        return PriceHistory(symbol = symbol, candles = candles)
    }

    override suspend fun sendTransaction(
        to: String,
        amount: BigDecimal,
        symbol: String
    ): Result<Transaction> {
        // Find the balance item
        val balanceItem = mockWallet.balances.find { it.symbol == symbol }
            ?: return Result.failure(Exception("Symbol $symbol not found"))

        // Check if sufficient balance
        if (balanceItem.quantity < amount) {
            return Result.failure(Exception("Insufficient balance for $symbol"))
        }

        val transaction = Transaction(
            id = "tx_${System.currentTimeMillis()}",
            from = getAddress(),
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.SUCCESS
        )
        return Result.success(transaction)
    }

    private fun generateMockCandles(symbol: String): List<PriceCandle> {
        val candles = mutableListOf<PriceCandle>()
        val basePrice = when (symbol) {
            "BTC" -> BigDecimal("26927.00")
            "ETH" -> BigDecimal("2012.48")
            "LTC" -> BigDecimal("1385.40")
            "XRP" -> BigDecimal("4.637")
            else -> BigDecimal("100.00")
        }

        var currentPrice = basePrice
        val now = System.currentTimeMillis()

        for (i in 0..29) {
            val change = BigDecimal(Math.random() * 200 - 100) // Random change -100 to 100
            val open = currentPrice
            val high = currentPrice + BigDecimal(Math.random() * 50)
            val low = currentPrice - BigDecimal(Math.random() * 50)
            val close = low + BigDecimal(Math.random() * (high.toDouble() - low.toDouble()))

            candles.add(
                PriceCandle(
                    timestamp = now - (29 - i) * 3600000L, // 1 hour intervals
                    open = open,
                    high = high,
                    low = low,
                    close = close,
                    change = change
                )
            )

            currentPrice = close
        }

        return candles.sortedBy { it.timestamp }
    }
}