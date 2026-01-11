package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/**
 * Minimal symbol price provider that pulls data from `SymbolPriceData`.
 * No business logic here — just direct returns of static data.
 */
interface SymbolPriceRepository {
    suspend fun getLatestPrice(symbol: String): SymbolPrice?
    suspend fun getHistory(symbol: String): PriceHistory?
}

class SymbolPriceRepositoryImpl : SymbolPriceRepository {
    override suspend fun getLatestPrice(symbol: String): SymbolPrice? = SymbolPriceData.latest[symbol]
    override suspend fun getHistory(symbol: String): PriceHistory? = SymbolPriceData.histories[symbol]
}
