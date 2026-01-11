package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.*

/**
 * Static sample data for Wallet domain. This file only contains plain data values
 * (no business logic) which repository implementations can return directly.
 */
object WalletData {
    val address: String = "0xSAMPLEADDRESS000000000000000000"

    val wallet: Wallet = Wallet(
        address = address,
        balances = listOf(
            BalanceItem(symbol = "BTC", name = "Bitcoin", quantity = 0.08, price = 26927.0),
            BalanceItem(symbol = "ETH", name = "Ethereum", quantity = 2.5, price = 2012.48),
            BalanceItem(symbol = "LTC", name = "Litecoin", quantity = 5.0, price = 138.54),
            BalanceItem(symbol = "XRP", name = "Ripple", quantity = 1000.0, price = 0.4637)
        )
    )

    val priceHistories: Map<String, PriceHistory> = mapOf(
        "BTC" to PriceHistory(
            symbol = "BTC",
            candles = listOf(
                PriceCandle(timestamp = 1_700_000_000_000L, open = 26000.0, high = 27000.0, low = 25900.0, close = 26927.0, change = 927.0),
                PriceCandle(timestamp = 1_700_000_360_000L, open = 26927.0, high = 27050.0, low = 26800.0, close = 26980.0, change = 53.0),
                PriceCandle(timestamp = 1_700_000_720_000L, open = 26980.0, high = 27100.0, low = 26900.0, close = 27050.0, change = 70.0)
            )
        ),
        "ETH" to PriceHistory(
            symbol = "ETH",
            candles = listOf(
                PriceCandle(timestamp = 1_700_000_000_000L, open = 1900.0, high = 2050.0, low = 1880.0, close = 2012.48, change = 112.48),
                PriceCandle(timestamp = 1_700_000_360_000L, open = 2012.48, high = 2030.0, low = 2000.0, close = 2018.0, change = 5.52)
            )
        )
    )

    val sampleTransaction: Transaction = Transaction(
        id = "tx_sample_0001",
        from = address,
        to = "0xDESTINATION00000000000000000000",
        amount = 0.01,
        symbol = "BTC",
        status = TransactionStatus.SUCCESS
    )
}
