package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.TransactionStatus

/** Fake transaction repo that returns a successful Transaction with provided inputs. */
class FakeTransactionRepository : TransactionRepository {
    override suspend fun sendTransaction(to: String, amount: Double, symbol: String): Result<Transaction> {
        val tx = Transaction(
            id = "tx-${System.currentTimeMillis()}",
            from = "", // caller may fill / consumer may set from
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.SUCCESS
        )
        return Result.success(tx)
    }
}
