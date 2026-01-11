package com.cryptx.cryptx.domain

/** Simple latest price for a symbol (cross-platform using Double) */
data class SymbolPrice(
    val symbol: String,
    val price: Double,
    val timestamp: Long = 0L
)
