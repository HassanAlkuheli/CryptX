package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.NetworkConfig

/** Mock network repo that always reports network available and returns a test config. */
class FakeNetworkRepository : NetworkRepository {
    override fun isNetworkAvailable(): Boolean = true

    fun getNetworkConfig(): NetworkConfig = NetworkConfig(name = "TESTNET", rpcUrl = "https://example.testnet")
}
