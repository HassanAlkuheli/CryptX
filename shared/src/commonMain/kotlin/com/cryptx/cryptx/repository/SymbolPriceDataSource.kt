package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceCandle
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/**
 * DataSource providing mock symbol price and price history data
 */
object SymbolPriceDataSource {

    private val symbolPrices = listOf(
        SymbolPrice(symbol = "BTC", price = 42000.0, timestamp = 1704931200000L),
        SymbolPrice(symbol = "ETH", price = 2200.0, timestamp = 1704931200000L),
        SymbolPrice(symbol = "SOL", price = 98.0, timestamp = 1704931200000L),
        SymbolPrice(symbol = "USDT", price = 1.0, timestamp = 1704931200000L),
        SymbolPrice(symbol = "ADA", price = 0.45, timestamp = 1704931200000L)
    )

    private val priceHistories = mapOf(
        "BTC" to PriceHistory(
            symbol = "BTC",
            candles = listOf(
                PriceCandle(timestamp = 1704844800000L, open = 41000.0, high = 42500.0, low = 40800.0, close = 42000.0, change = 2.44),
                PriceCandle(timestamp = 1704758400000L, open = 40500.0, high = 41200.0, low = 40000.0, close = 41000.0, change = 1.23),
                PriceCandle(timestamp = 1704672000000L, open = 40000.0, high = 40800.0, low = 39500.0, close = 40500.0, change = 1.25),
                PriceCandle(timestamp = 1704585600000L, open = 39800.0, high = 40200.0, low = 39200.0, close = 40000.0, change = 0.50),
                PriceCandle(timestamp = 1704499200000L, open = 39500.0, high = 40000.0, low = 39000.0, close = 39800.0, change = 0.76)
            )
        ),
        "ETH" to PriceHistory(
            symbol = "ETH",
            candles = listOf(
                PriceCandle(timestamp = 1704844800000L, open = 2150.0, high = 2250.0, low = 2100.0, close = 2200.0, change = 2.33),
                PriceCandle(timestamp = 1704758400000L, open = 2100.0, high = 2180.0, low = 2050.0, close = 2150.0, change = 2.38),
                PriceCandle(timestamp = 1704672000000L, open = 2080.0, high = 2120.0, low = 2000.0, close = 2100.0, change = 0.96),
                PriceCandle(timestamp = 1704585600000L, open = 2050.0, high = 2100.0, low = 2020.0, close = 2080.0, change = 1.46),
                PriceCandle(timestamp = 1704499200000L, open = 2000.0, high = 2060.0, low = 1980.0, close = 2050.0, change = 2.50)
            )
        ),
        "SOL" to PriceHistory(
            symbol = "SOL",
            candles = listOf(
                PriceCandle(timestamp = 1704844800000L, open = 95.0, high = 100.0, low = 93.0, close = 98.0, change = 3.16),
                PriceCandle(timestamp = 1704758400000L, open = 92.0, high = 96.0, low = 90.0, close = 95.0, change = 3.26),
                PriceCandle(timestamp = 1704672000000L, open = 90.0, high = 93.0, low = 88.0, close = 92.0, change = 2.22),
                PriceCandle(timestamp = 1704585600000L, open = 88.0, high = 91.0, low = 86.0, close = 90.0, change = 2.27),
                PriceCandle(timestamp = 1704499200000L, open = 85.0, high = 89.0, low = 84.0, close = 88.0, change = 3.53)
            )
        )
    )

    fun getAllPrices(): List<SymbolPrice> = symbolPrices

    fun getPriceBySymbol(symbol: String): SymbolPrice? =
        symbolPrices.find { it.symbol == symbol }

    fun getPriceHistory(symbol: String): PriceHistory? =
        priceHistories[symbol]
}
