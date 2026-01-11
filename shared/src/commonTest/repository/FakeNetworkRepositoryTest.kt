package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Network
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class FakeNetworkRepositoryTest {

    private val repository = FakeNetworkRepository()

    @Test
    fun `isNetworkAvailable returns true`() = runTest {
        val available = repository.isNetworkAvailable()
        assertTrue(available)
    }

    @Test
    fun `getCurrentNetwork returns MAINNET`() = runTest {
        val network = repository.getCurrentNetwork()
        assertEquals(Network.MAINNET, network)
    }
}