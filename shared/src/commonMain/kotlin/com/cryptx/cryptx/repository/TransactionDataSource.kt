package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.TransactionStatus

/**
 * DataSource providing mock transaction data
 */
object TransactionDataSource {

    private val transactions = mutableListOf(
        Transaction(
            id = "tx_001",
            from = "0x1234567890abcdef1234567890abcdef12345678",
            to = "0xabcdef1234567890abcdef1234567890abcdef12",
            amount = 0.5,
            symbol = "BTC",
            status = TransactionStatus.SUCCESS
        ),
        Transaction(
            id = "tx_002",
            from = "0x1234567890abcdef1234567890abcdef12345678",
            to = "0x9876543210fedcba9876543210fedcba98765432",
            amount = 2.0,
            symbol = "ETH",
            status = TransactionStatus.SUCCESS
        ),
        Transaction(
            id = "tx_003",
            from = "0xabcdef1234567890abcdef1234567890abcdef12",
            to = "0x1234567890abcdef1234567890abcdef12345678",
            amount = 100.0,
            symbol = "USDT",
            status = TransactionStatus.PENDING
        ),
        Transaction(
            id = "tx_004",
            from = "0x1234567890abcdef1234567890abcdef12345678",
            to = "0xfedcba9876543210fedcba9876543210fedcba98",
            amount = 5.0,
            symbol = "SOL",
            status = TransactionStatus.FAILED
        )
    )

    private var transactionCounter = 5

    fun getAllTransactions(): List<Transaction> = transactions.toList()

    fun getTransactionById(id: String): Transaction? =
        transactions.find { it.id == id }

    fun getTransactionsByAddress(address: String): List<Transaction> =
        transactions.filter { it.from == address || it.to == address }

    fun getTransactionsByStatus(status: TransactionStatus): List<Transaction> =
        transactions.filter { it.status == status }

    fun addTransaction(from: String, to: String, amount: Double, symbol: String): Transaction {
        val txId = transactionCounter++
        val paddedId = txId.toString().padStart(3, '0')
        val newTransaction = Transaction(
            id = "tx_$paddedId",
            from = from,
            to = to,
            amount = amount,
            symbol = symbol,
            status = TransactionStatus.PENDING
        )
        transactions.add(newTransaction)
        return newTransaction
    }
}
