package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction

/** Repository for transaction operations. */
interface TransactionRepository {
    suspend fun sendTransaction(to: String, amount: Double, symbol: String): Result<Transaction>
}
