package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Transaction

/** Send transaction UI state (no logic) */
data class SendTransactionState(
    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val error: String? = null
)
