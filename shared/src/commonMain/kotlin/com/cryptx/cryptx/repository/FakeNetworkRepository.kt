package com.cryptx.cryptx.repository

/** Compatibility wrapper kept for callers: delegates to NetworkRepositoryImpl backed by FakeNetworkDataSource. */
class FakeNetworkRepository : NetworkRepository {
    private val impl: NetworkRepository = NetworkRepositoryImpl(FakeNetworkDataSource())

    override suspend fun isNetworkAvailable(): Boolean = impl.isNetworkAvailable()
    override suspend fun getCurrentNetwork(): com.cryptx.cryptx.domain.Network = impl.getCurrentNetwork()
}