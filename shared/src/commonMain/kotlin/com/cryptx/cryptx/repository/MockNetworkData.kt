package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

/** Static mock network data provider */
object MockNetworkData {
    val isNetworkAvailable: Boolean = true
    val currentNetwork: Network = Network.MAINNET
}
