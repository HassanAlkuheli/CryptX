package com.cryptx.cryptx.state

import com.cryptx.cryptx.domain.Profile

/** Profile screen state (no logic) */
data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
