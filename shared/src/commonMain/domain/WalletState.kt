package com.cryptx.cryptx.domain

data class WalletState(
    val wallet: Wallet? = null,
    val transactions: List<Transaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)