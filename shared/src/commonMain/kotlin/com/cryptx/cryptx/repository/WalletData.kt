package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/** Mock wallet data provider. No repository logic here — only data and helpers. */
internal object WalletData {
    val wallet = Wallet(
        address = "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P",
        balances = listOf(
            BalanceItem(
                symbol = "BTC",
                name = "Bitcoin",
                quantity = 0.08,
                price = 26927.00
            ),
            BalanceItem(
                symbol = "ETH",
                name = "Ethereum",
                quantity = 2.5,
                price = 2012.48
            ),
            BalanceItem(
                symbol = "LTC",
                name = "Litecoin",
                quantity = 5.0,
                price = 1385.40
            ),
            BalanceItem(
                symbol = "XRP",
                name = "Ripple",
                quantity = 1000.0,
                price = 4.637
            )
        )
    )
}
