package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

/** Simple network mock provider */
internal object NetworkData {
    fun isNetworkAvailable(): Boolean = true
    fun currentNetwork(): Network = Network.MAINNET
}
