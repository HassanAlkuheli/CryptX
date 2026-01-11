package com.cryptx.cryptx.repository

/** Compatibility wrapper kept for tests and callers: delegates to a thin repository implementation backed by a FakeWalletDataSource. */
class FakeWalletRepository : WalletRepository {
    private val impl: WalletRepository = WalletRepositoryImpl(FakeWalletDataSource())

    override suspend fun getAddress(): String = impl.getAddress()
    override suspend fun getWallet() = impl.getWallet()
    override suspend fun getPriceHistory(symbol: String) = impl.getPriceHistory(symbol)
    override suspend fun sendTransaction(to: String, amount: Double, symbol: String) = impl.sendTransaction(to, amount, symbol)
}