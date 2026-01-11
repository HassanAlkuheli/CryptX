package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.SymbolPrice

/** Price list state (no logic) */
data class PriceState(
    val prices: List<SymbolPrice> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
