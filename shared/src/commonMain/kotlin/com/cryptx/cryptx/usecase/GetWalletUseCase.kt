package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.PriceHistory
import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.repository.WalletRepository

class GetWalletUseCase(
    private val walletRepository: WalletRepository
) {

    suspend fun execute(): Wallet {
        return walletRepository.getWallet()
    }

    suspend fun getPriceHistory(symbol: String): PriceHistory {
        return walletRepository.getPriceHistory(symbol)
    }
}
