package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.PriceHistory

/** Price history state (no logic) */
data class PriceHistoryState(
    val history: PriceHistory? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
