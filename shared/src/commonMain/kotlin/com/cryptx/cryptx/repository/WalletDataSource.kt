package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/**
 * Simple data provider file for wallet domain. No logic here: it just returns
 * data (sourced from mock providers or real data sources later).
 */
object WalletDataSource {
    suspend fun getAddress(): String = MockWalletData.wallet.address

    suspend fun getWallet(): Wallet = MockWalletData.wallet

    suspend fun getPriceHistory(symbol: String): PriceHistory = MockWalletData.priceHistories[symbol]
        ?: PriceHistory(symbol = symbol, candles = emptyList())

    suspend fun buildTransaction(id: String, to: String, amount: Double, symbol: String): Transaction {
        return MockWalletData.buildTransaction(id = id, from = MockWalletData.wallet.address, to = to, amount = amount, symbol = symbol)
    }
}
