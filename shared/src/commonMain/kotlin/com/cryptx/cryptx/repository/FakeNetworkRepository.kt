package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

class FakeNetworkRepository : NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean {
        return true
    }

    override suspend fun getCurrentNetwork(): Network {
        return Network.MAINNET
    }
}