package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Holding

/** Simple state holder for wallet screen; no logic or computed properties */
data class WalletState(
    val address: String = "",
    val holdings: List<Holding> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
