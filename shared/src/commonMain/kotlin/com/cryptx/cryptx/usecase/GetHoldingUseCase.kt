package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Holding
import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.repository.SymbolPriceRepository
import com.cryptx.cryptx.repository.WalletRepository

/** Use case to compose a Holding by combining wallet balance and price data */
class GetHoldingUseCase(
    private val walletRepository: WalletRepository,
    private val priceRepository: SymbolPriceRepository
) {

    suspend fun execute(symbol: String): Holding? {
        val balance = walletRepository.getBalanceBySymbol(symbol) ?: return null
        val price = priceRepository.getPriceBySymbol(symbol)?.price ?: 0.0
        val history: PriceHistory? = priceRepository.getPriceHistory(symbol)
        val eq = price * balance.quantity

        return Holding(
            symbol = balance.symbol,
            name = balance.name,
            amount = balance.quantity,
            currentPrice = price,
            eqToUsdt = eq,
            priceHistory = history
        )
    }
}
