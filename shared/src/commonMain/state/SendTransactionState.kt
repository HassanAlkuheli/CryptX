package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Transaction

data class SendTransactionState(
    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val error: String? = null,
    val isNetworkAvailable: Boolean = true
)