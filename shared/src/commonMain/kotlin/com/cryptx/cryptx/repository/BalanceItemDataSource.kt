package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem

/**
 * DataSource providing mock balance item data
 */
object BalanceItemDataSource {

    private val balanceItems = listOf(
        BalanceItem(
            symbol = "BTC",
            name = "Bitcoin",
            quantity = 1.5,
            price = 42000.0
        ),
        BalanceItem(
            symbol = "ETH",
            name = "Ethereum",
            quantity = 10.0,
            price = 2200.0
        ),
        BalanceItem(
            symbol = "SOL",
            name = "Solana",
            quantity = 50.0,
            price = 98.0
        ),
        BalanceItem(
            symbol = "USDT",
            name = "Tether",
            quantity = 5000.0,
            price = 1.0
        ),
        BalanceItem(
            symbol = "ADA",
            name = "Cardano",
            quantity = 2000.0,
            price = 0.45
        )
    )

    fun getAll(): List<BalanceItem> = balanceItems

    fun getBySymbol(symbol: String): BalanceItem? =
        balanceItems.find { it.symbol == symbol }
}
