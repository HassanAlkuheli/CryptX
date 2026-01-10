package com.cryptx.cryptx.state

import com.example.crypto_wallet.domain.Transaction

data class SendTransactionState(
    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val error: String? = null
)