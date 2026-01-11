package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

class FakeNetworkRepository : NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean {
        return NetworkDataSource.isNetworkAvailable()
    }

    override suspend fun getCurrentNetwork(): Network {
        return NetworkDataSource.getCurrentNetwork()
    }
}