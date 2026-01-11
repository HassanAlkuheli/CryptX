package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network

interface NetworkRepository {
    suspend fun isNetworkAvailable(): Boolean
    suspend fun getCurrentNetwork(): Network
}
