package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem
import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Transaction

/**
 * Repository for Wallet data - no logic, just pull and return
 */
interface WalletRepository {
    suspend fun getAddress(): String
    suspend fun getWallet(): Wallet
    suspend fun getBalances(): List<BalanceItem>
    suspend fun getBalanceBySymbol(symbol: String): BalanceItem?
    suspend fun getPriceHistory(symbol: String): PriceHistory
    suspend fun sendTransaction(to: String, amount: Double, symbol: String): Result<Transaction>
}

class WalletRepositoryImpl : WalletRepository {

    override suspend fun getAddress(): String {
        return WalletDataSource.getAddress()
    }

    override suspend fun getWallet(): Wallet {
        return WalletDataSource.getWallet()
    }

    override suspend fun getBalances(): List<BalanceItem> {
        return WalletDataSource.getBalances()
    }

    override suspend fun getBalanceBySymbol(symbol: String): BalanceItem? {
        return WalletDataSource.getBalanceBySymbol(symbol)
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory {
        return SymbolPriceDataSource.getPriceHistory(symbol) ?: PriceHistory(symbol = symbol, candles = emptyList())
    }

    override suspend fun sendTransaction(to: String, amount: Double, symbol: String): Result<Transaction> {
        return try {
            val tx = TransactionDataSource.addTransaction(WalletDataSource.getAddress(), to, amount, symbol)
            Result.success(tx)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}
