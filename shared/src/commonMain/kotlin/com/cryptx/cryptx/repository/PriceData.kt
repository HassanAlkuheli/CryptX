package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceCandle
import com.cryptx.cryptx.domain.PriceHistory

/** Provides price history mock data. Keep generation detail inside data provider (no logic in repo). */
internal object PriceData {
    fun getPriceHistory(symbol: String): PriceHistory {
        val candles = generateMockCandles(symbol)
        return PriceHistory(symbol = symbol, candles = candles)
    }

    private fun generateMockCandles(symbol: String): List<PriceCandle> {
        val basePrice = when (symbol) {
            "BTC" -> 26927.00
            "ETH" -> 2012.48
            "LTC" -> 1385.40
            "XRP" -> 4.637
            else -> 100.00
        }

        val candles = mutableListOf<PriceCandle>()
        var currentPrice = basePrice
        val now = System.currentTimeMillis()

        for (i in 0..29) {
            val change = kotlin.random.Random.nextDouble(-100.0, 100.0)
            val open = currentPrice
            val high = currentPrice + kotlin.random.Random.nextDouble(0.0, 50.0)
            val low = currentPrice - kotlin.random.Random.nextDouble(0.0, 50.0)
            val close = low + kotlin.random.Random.nextDouble(0.0, (high - low))

            candles.add(
                PriceCandle(
                    timestamp = now - (29 - i) * 3600000L,
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
