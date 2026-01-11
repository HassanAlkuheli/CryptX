package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceCandle
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/**
 * Static sample data for symbol latest price and histories. No logic, just values.
 */
object SymbolPriceData {
    val latest: Map<String, SymbolPrice> = mapOf(
        "BTC" to SymbolPrice(symbol = "BTC", price = 26927.0, timestamp = 1_700_000_900_000L),
        "ETH" to SymbolPrice(symbol = "ETH", price = 2012.48, timestamp = 1_700_000_900_000L),
        "LTC" to SymbolPrice(symbol = "LTC", price = 138.54, timestamp = 1_700_000_900_000L)
    )

    val histories: Map<String, PriceHistory> = mapOf(
        "BTC" to PriceHistory(
            symbol = "BTC",
            candles = listOf(
                PriceCandle(timestamp = 1_700_000_000_000L, open = 26000.0, high = 27000.0, low = 25900.0, close = 26927.0, change = 927.0),
                PriceCandle(timestamp = 1_700_000_360_000L, open = 26927.0, high = 27050.0, low = 26800.0, close = 26980.0, change = 53.0)
            )
        ),
        "ETH" to PriceHistory(
            symbol = "ETH",
            candles = listOf(
                PriceCandle(timestamp = 1_700_000_000_000L, open = 1900.0, high = 2050.0, low = 1880.0, close = 2012.48, change = 112.48)
            )
        )
    )
}
