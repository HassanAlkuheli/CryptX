package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/**
 * Static mock data for wallet-related domains. The repository will pull from here.
 * Keep generation inside this file so repository implementations remain simple and
 * only return data.
 */
object MockWalletData {
    val wallet: Wallet = Wallet(
        address = "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P",
        balances = listOf(
            BalanceItem(symbol = "BTC", name = "Bitcoin", quantity = 0.08, price = 26927.00),
            BalanceItem(symbol = "ETH", name = "Ethereum", quantity = 2.5, price = 2012.48),
            BalanceItem(symbol = "LTC", name = "Litecoin", quantity = 5.0, price = 1385.40),
            BalanceItem(symbol = "XRP", name = "Ripple", quantity = 1000.0, price = 4.637)
        )
    )

    val priceHistories: Map<String, PriceHistory> = listOf("BTC", "ETH", "LTC", "XRP").associateWith { symbol ->
        PriceHistory(symbol = symbol, candles = generateCandlesFor(symbol))
    }

    private fun generateCandlesFor(symbol: String): List<PriceCandle> {
        val basePrice = when (symbol) {
            "BTC" -> 26927.0
            "ETH" -> 2012.48
            "LTC" -> 1385.40
            "XRP" -> 4.637
            else -> 100.0
        }

        val now = System.currentTimeMillis()
        val candles = (0 until 30).map { i ->
            val offset = i * 3600000L
            val open = basePrice + (i - 15) * 1.0
            val close = open + (i % 5 - 2) * 0.25
            PriceCandle(
                timestamp = now - ((29 - i) * 3600000L),
                open = open,
                high = maxOf(open, close) + 1.0,
                low = minOf(open, close) - 1.0,
                close = close,
                change = close - open
            )
        }
        return candles
    }

    fun buildTransaction(id: String, from: String, to: String, amount: Double, symbol: String): Transaction {
        return Transaction(
            id = id,
            from = from,
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.SUCCESS
        )
    }

    val exampleTransaction: Transaction = buildTransaction(
        id = "tx_example_001",
        from = wallet.address,
        to = "1Z2Y3X4W5V6U7T8S9R0",
        amount = 0.01,
        symbol = "BTC"
    )
}
