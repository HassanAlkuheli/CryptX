package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

class FakeNetworkDataSource : NetworkDataSource {
    override suspend fun isNetworkAvailable(): Boolean = true
    override suspend fun getCurrentNetwork(): Network = Network.MAINNET
}