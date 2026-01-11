package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

/** Thin repository implementation that delegates to a data source and contains no business logic. */
class NetworkRepositoryImpl(
    private val dataSource: NetworkDataSource
) : NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean = dataSource.isNetworkAvailable()
    override suspend fun getCurrentNetwork(): Network = dataSource.getCurrentNetwork()
}