package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/**
 * Repository for SymbolPrice and PriceHistory data - no logic, just pull and return
 */
interface SymbolPriceRepository {
    suspend fun getAllPrices(): List<SymbolPrice>
    suspend fun getPriceBySymbol(symbol: String): SymbolPrice?
    suspend fun getPriceHistory(symbol: String): PriceHistory?
}

class SymbolPriceRepositoryImpl : SymbolPriceRepository {

    override suspend fun getAllPrices(): List<SymbolPrice> {
        return SymbolPriceDataSource.getAllPrices()
    }

    override suspend fun getPriceBySymbol(symbol: String): SymbolPrice? {
        return SymbolPriceDataSource.getPriceBySymbol(symbol)
    }

    override suspend fun getPriceHistory(symbol: String): PriceHistory? {
        return SymbolPriceDataSource.getPriceHistory(symbol)
    }
}
