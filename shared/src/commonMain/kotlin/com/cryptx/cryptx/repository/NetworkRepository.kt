package com.cryptx.cryptx.repository

/** Simple network repository interface used by use-cases. */
interface NetworkRepository {
    fun isNetworkAvailable(): Boolean
}
