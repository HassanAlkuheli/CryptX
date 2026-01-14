package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network
import com.cryptx.cryptx.domain.NetworkConfig

class FakeNetworkRepository : NetworkRepository {

    override suspend fun getCurrentNetwork(): Network = Network.MAINNET

    override suspend fun setCurrentNetwork(network: Network) {
        // no-op
    }

    override suspend fun getNetworkConfig(network: Network): NetworkConfig? = null

    override suspend fun getAllNetworks(): List<Network> = listOf(Network.MAINNET, Network.TESTNET)

    override suspend fun isNetworkAvailable(): Boolean = true
}