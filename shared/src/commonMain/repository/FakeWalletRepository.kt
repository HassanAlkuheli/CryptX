package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network
import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.TransactionStatus
import java.math.BigDecimal

class FakeWalletRepository : WalletRepository {
    override suspend fun getAddress(): String {
        return "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P"
    }

    override suspend fun getBalance(): BigDecimal {
        return BigDecimal("100.0")
    }

    override suspend fun sendTransaction(
        to: String,
        amount: BigDecimal
    ): Result<Transaction> {
        val transaction = Transaction(
            id = "tx_${System.currentTimeMillis()}",
            from = getAddress(),
            to = to,
            amount = amount,
            status = TransactionStatus.SUCCESS
        )
        return Result.success(transaction)
    }
}