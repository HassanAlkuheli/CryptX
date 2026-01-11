package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem

/**
 * Repository for BalanceItem data - no logic, just pull and return
 */
interface BalanceItemRepository {
    suspend fun getAllBalances(): List<BalanceItem>
    suspend fun getBalanceBySymbol(symbol: String): BalanceItem?
}

class BalanceItemRepositoryImpl : BalanceItemRepository {

    override suspend fun getAllBalances(): List<BalanceItem> {
        return BalanceItemDataSource.getAll()
    }

    override suspend fun getBalanceBySymbol(symbol: String): BalanceItem? {
        return BalanceItemDataSource.getBySymbol(symbol)
    }
}
