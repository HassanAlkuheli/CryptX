package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network
import com.cryptx.cryptx.domain.NetworkConfig

/**
 * Repository for Network data - no logic, just pull and return
 */
interface NetworkRepository {
    suspend fun getCurrentNetwork(): Network
    suspend fun setCurrentNetwork(network: Network)
    suspend fun getNetworkConfig(network: Network): NetworkConfig?
    suspend fun getAllNetworks(): List<Network>
    suspend fun isNetworkAvailable(): Boolean
}

class NetworkRepositoryImpl : NetworkRepository {

    override suspend fun getCurrentNetwork(): Network {
        return NetworkDataSource.getCurrentNetwork()
    }

    override suspend fun setCurrentNetwork(network: Network) {
        NetworkDataSource.setCurrentNetwork(network)
    }

    override suspend fun getNetworkConfig(network: Network): NetworkConfig? {
        return NetworkDataSource.getNetworkConfig(network)
    }

    override suspend fun getAllNetworks(): List<Network> {
        return NetworkDataSource.getAllNetworks()
    }

    override suspend fun isNetworkAvailable(): Boolean {
        // Mock: assume network is available when current network is MAINNET
        return NetworkDataSource.getCurrentNetwork() == Network.MAINNET
    }
}
