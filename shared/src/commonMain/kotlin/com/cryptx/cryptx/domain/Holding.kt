package com.cryptx.cryptx.domain

/**
 * Represents a holding for a symbol combining balance and price information
 */
data class Holding(
    val symbol: String,
    val name: String,
    val amount: Double,
    val currentPrice: Double,
    val eqToUsdt: Double,
    val priceHistory: PriceHistory?
)
