package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/** Fake data source that contains mock data / generation logic. */
class FakeWalletDataSource : WalletDataSource {
    private val mockWallet = Wallet(
        address = "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P",
        balances = listOf(
            BalanceItem(
                symbol = "BTC",
                name = "Bitcoin",
                quantity = 0.08,
                price = 26927.00
            ),
            BalanceItem(
                symbol = "ETH",
                name = "Ethereum",
                quantity = 2.5,
                price = 2012.48
            ),
            BalanceItem(
                symbol = "LTC",
                name = "Litecoin",
                quantity = 5.0,
                price = 1385.40
            ),
            BalanceItem(
                symbol = "XRP",
                name = "Ripple",
                quantity = 1000.0,
                price = 4.637
            )
        )
    )

    override suspend fun getAddress(): String = mockWallet.address

    override suspend fun getWallet(): Wallet = mockWallet

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        val candles = generateMockCandles(symbol)
        return PriceHistory(symbol = symbol, candles = candles)
    }

    override suspend fun sendTransaction(
        to: String,
        amount: Double,
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
            id = "tx_${kotlin.random.Random.nextLong()}",
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
            "BTC" -> 26927.00
            "ETH" -> 2012.48
            "LTC" -> 1385.40
            "XRP" -> 4.637
            else -> 100.00
        }

        var currentPrice = basePrice
        val now = kotlin.random.Random.nextLong()

        for (i in 0..29) {
            val change = kotlin.random.Random.nextDouble(-100.0, 100.0)
            val open = currentPrice
            val high = currentPrice + kotlin.random.Random.nextDouble(0.0, 50.0)
            val low = currentPrice - kotlin.random.Random.nextDouble(0.0, 50.0)
            val close = low + kotlin.random.Random.nextDouble(0.0, (high - low))

            candles.add(
                PriceCandle(
                    timestamp = now - (29 - i) * 3600000L, // 1 hour intervals (synthetic)
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