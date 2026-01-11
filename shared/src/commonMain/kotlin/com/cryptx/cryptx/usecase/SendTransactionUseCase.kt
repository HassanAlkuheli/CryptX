package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.repository.NetworkRepository
import com.cryptx.cryptx.repository.WalletRepository

class SendTransactionUseCase(
    private val walletRepository: WalletRepository,
    private val networkRepository: NetworkRepository,
    private val validateAddressUseCase: ValidateAddressUseCase
) {

    suspend fun execute(
        to: String,
        amount: Double,
        symbol: String
    ): Result<Transaction> {

        if (!networkRepository.isNetworkAvailable()) {
            return Result.failure(Exception("No network"))
        }

        if (!validateAddressUseCase.execute(to)) {
            return Result.failure(Exception("Invalid address"))
        }

        val wallet = walletRepository.getWallet()
        val balanceItem = wallet.balances.find { it.symbol == symbol }
            ?: return Result.failure(Exception("Symbol $symbol not found"))

        if (balanceItem.quantity < amount) {
            return Result.failure(Exception("Insufficient balance for $symbol"))
        }

        return walletRepository.sendTransaction(to, amount, symbol)
    }
}