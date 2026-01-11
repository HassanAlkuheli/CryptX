package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceCandle
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/** Simple fake price repo that returns deterministic mock data (no business logic). */
class FakePriceRepository : PriceRepository {
    override suspend fun getSymbolPrice(symbol: String): SymbolPrice {
        // Fixed mock price
        return SymbolPrice(symbol = symbol, price = 42.0, timestamp = 0L)
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        val candles = (0 until 10).map { i ->
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
}
