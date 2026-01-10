package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction
import java.math.BigDecimal

interface WalletRepository {
    suspend fun getAddress(): String
    suspend fun getBalance(): BigDecimal
    suspend fun sendTransaction(
        to: String,
        amount: BigDecimal
    ): Result<Transaction>
}