package com.cryptx.cryptx.domain

/** Represents an asset balance using Double for cross-platform compatibility */
data class BalanceItem(
    val symbol: String,
    val name: String,
    val quantity: Double,
    val price: Double,
    val totalValue: Double = quantity * price
)
