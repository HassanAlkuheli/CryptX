package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Wallet
import com.cryptx.cryptx.repository.WalletRepository
import java.math.BigDecimal

class GetWalletUseCase(
    private val walletRepository: WalletRepository
) {

    suspend fun execute(): Wallet {
        return Wallet(
            address = walletRepository.getAddress(),
            balance = walletRepository.getBalance()
        )
    }
}