package com.cryptx.cryptx.domain

/** Wallet aggregate with Double-based balances */
data class Wallet(
    val address: String,
    val balances: List<BalanceItem>
) {
    val totalBalance: Double
        get() = balances.fold(0.0) { acc, item -> acc + item.totalValue }
}
