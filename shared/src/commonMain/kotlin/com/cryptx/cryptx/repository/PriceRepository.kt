package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.SymbolPrice

/** Repository for price-related data (symbol latest price and history). */
interface PriceRepository {
    suspend fun getSymbolPrice(symbol: String): SymbolPrice
    suspend fun getPriceHistory(symbol: String): PriceHistory
}
