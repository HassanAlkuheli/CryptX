package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.domain.Transaction

data class WalletState(
    val wallet: Wallet? = null,
    val transactions: List<Transaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)