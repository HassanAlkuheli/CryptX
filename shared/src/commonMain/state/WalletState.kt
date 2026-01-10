package com.cryptx.cryptx.state

import com.example.crypto_wallet.domain.Wallet

data class WalletState(
    val wallet: Wallet? = null,
    val transactions: List<com.example.crypto_wallet.domain.Transaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)