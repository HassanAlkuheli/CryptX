package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.domain.TransactionStatus

/**
 * Repository for Transaction data - no logic, just pull and return
 */
interface TransactionRepository {
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun getTransactionById(id: String): Transaction?
    suspend fun getTransactionsByAddress(address: String): List<Transaction>
    suspend fun getTransactionsByStatus(status: TransactionStatus): List<Transaction>
    suspend fun addTransaction(from: String, to: String, amount: Double, symbol: String): Transaction
}

class TransactionRepositoryImpl : TransactionRepository {

    override suspend fun getAllTransactions(): List<Transaction> {
        return TransactionDataSource.getAllTransactions()
    }

    override suspend fun getTransactionById(id: String): Transaction? {
        return TransactionDataSource.getTransactionById(id)
    }

    override suspend fun getTransactionsByAddress(address: String): List<Transaction> {
        return TransactionDataSource.getTransactionsByAddress(address)
    }

    override suspend fun getTransactionsByStatus(status: TransactionStatus): List<Transaction> {
        return TransactionDataSource.getTransactionsByStatus(status)
    }

    override suspend fun addTransaction(from: String, to: String, amount: Double, symbol: String): Transaction {
        return TransactionDataSource.addTransaction(from, to, amount, symbol)
    }
}
