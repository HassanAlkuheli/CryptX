package com.cryptx.cryptx.domain

import java.math.BigDecimal

data class Wallet(
    val address: String,
    val balance: BigDecimal
)