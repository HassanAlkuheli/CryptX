package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.BalanceItem
import com.cryptx.cryptx.domain.Wallet

/**
 * DataSource providing mock wallet data
 */
object WalletDataSource {

    private val walletAddress = "0x1234567890abcdef1234567890abcdef12345678"

    private val walletBalances = listOf(
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

    fun getAddress(): String = walletAddress

    fun getWallet(): Wallet = Wallet(
        address = walletAddress,
        balances = walletBalances
    )

    fun getBalances(): List<BalanceItem> = walletBalances

    fun getBalanceBySymbol(symbol: String): BalanceItem? =
        walletBalances.find { it.symbol == symbol }
}
