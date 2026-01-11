package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

/** Network repository that pulls from the network data provider. */
class FakeNetworkRepository : NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean = NetworkData.isNetworkAvailable()

    override suspend fun getCurrentNetwork(): Network = NetworkData.currentNetwork()
}