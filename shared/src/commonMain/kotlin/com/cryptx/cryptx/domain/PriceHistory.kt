package com.cryptx.cryptx.domain

/** Price history candle using Double values for cross-platform compatibility */
data class PriceCandle(
    val timestamp: Long,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val change: Double
)

data class PriceHistory(
    val symbol: String,
    val candles: List<PriceCandle>
)
