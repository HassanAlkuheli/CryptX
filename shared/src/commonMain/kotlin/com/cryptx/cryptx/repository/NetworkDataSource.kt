package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network
import com.cryptx.cryptx.domain.NetworkConfig

/**
 * DataSource providing mock network configuration data
 */
object NetworkDataSource {

    private val networkConfigs = mapOf(
        Network.MAINNET to NetworkConfig(
            name = "Ethereum Mainnet",
            rpcUrl = "https://mainnet.infura.io/v3/mock-api-key"
        ),
        Network.TESTNET to NetworkConfig(
            name = "Goerli Testnet",
            rpcUrl = "https://goerli.infura.io/v3/mock-api-key"
        )
    )

    private var currentNetwork: Network = Network.MAINNET

    fun getCurrentNetwork(): Network = currentNetwork

    fun setCurrentNetwork(network: Network) {
        currentNetwork = network
    }

    fun getNetworkConfig(network: Network): NetworkConfig? =
        networkConfigs[network]

    fun getAllNetworks(): List<Network> = Network.entries
}
