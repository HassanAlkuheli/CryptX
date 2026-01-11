package com.cryptx.cryptx.domain

import java.math.BigDecimal

data class BalanceItem(
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val totalValue: BigDecimal = quantity * price
)

data class PriceCandle(
    val timestamp: Long,
    val open: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val close: BigDecimal,
    val change: BigDecimal
)

data class PriceHistory(
    val symbol: String,
    val candles: List<PriceCandle>
)

data class Wallet(
    val address: String,
    val balances: List<BalanceItem>
) {
    val totalBalance: BigDecimal
        get() = balances.fold(BigDecimal.ZERO) { acc, item -> acc + item.totalValue }
}