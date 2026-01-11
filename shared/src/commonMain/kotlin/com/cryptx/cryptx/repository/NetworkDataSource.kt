package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

/** Simple data source for network info, returns mock values from MockNetworkData */
object NetworkDataSource {
    suspend fun isNetworkAvailable(): Boolean = MockNetworkData.isNetworkAvailable

    suspend fun getCurrentNetwork(): Network = MockNetworkData.currentNetwork
}
