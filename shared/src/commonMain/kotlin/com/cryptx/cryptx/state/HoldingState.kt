package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Holding

/** State for a single holding (no logic) */
data class HoldingState(
    val holding: Holding? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
