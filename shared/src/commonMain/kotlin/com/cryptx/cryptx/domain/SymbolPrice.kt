package com.cryptx.cryptx.domain

/** Simple latest price for a symbol (cross-platform using Double) */
data class SymbolPrice(
    val symbol: String,
    val price: Double,
    val timestamp: Long = 0L
)

/** Price history candle using Double values for cross-platform compatibility */
data class PriceCandle(
    val timestamp: Long,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val change: Double
)

/** Historical price series for a symbol */
data class PriceHistory(
    val symbol: String,
    val candles: List<PriceCandle>
)
