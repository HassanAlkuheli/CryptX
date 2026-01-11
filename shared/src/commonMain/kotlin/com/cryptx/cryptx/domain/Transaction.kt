package com.cryptx.cryptx.domain

/** Transaction entity (amount is Double for platform neutrality) */
data class Transaction(
    val id: String,
    val from: String,
    val to: String,
    val amount: Double,
    val symbol: String,
    val status: TransactionStatus
)

enum class TransactionStatus {
    PENDING,
    SUCCESS,
    FAILED
}