package com.cryptx.cryptx.domain

import java.math.BigDecimal

data class Transaction(
    val id: String,
    val from: String,
    val to: String,
    val amount: BigDecimal,
    val symbol: String,
    val status: TransactionStatus
)

enum class TransactionStatus {
    PENDING,
    SUCCESS,
    FAILED
}