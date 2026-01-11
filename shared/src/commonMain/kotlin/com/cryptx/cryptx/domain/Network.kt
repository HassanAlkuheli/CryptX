package com.cryptx.cryptx.domain

enum class Network {
    MAINNET,
    TESTNET
}

data class NetworkConfig(
    val name: String,
    val rpcUrl: String
)