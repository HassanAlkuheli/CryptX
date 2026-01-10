package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Transaction
import com.cryptx.cryptx.repository.NetworkRepository
import com.cryptx.cryptx.repository.WalletRepository
import java.math.BigDecimal

class SendTransactionUseCase(
    private val walletRepository: WalletRepository,
    private val networkRepository: NetworkRepository,
    private val validateAddressUseCase: ValidateAddressUseCase
) {

    suspend fun execute(
        to: String,
        amount: BigDecimal
    ): Result<Transaction> {

        if (!networkRepository.isNetworkAvailable()) {
            return Result.failure(Exception("No network"))
        }

        if (!validateAddressUseCase.execute(to)) {
            return Result.failure(Exception("Invalid address"))
        }

        val balance = walletRepository.getBalance()
        if (balance < amount) {
            return Result.failure(Exception("Insufficient balance"))
        }

        return walletRepository.sendTransaction(to, amount)
    }
}