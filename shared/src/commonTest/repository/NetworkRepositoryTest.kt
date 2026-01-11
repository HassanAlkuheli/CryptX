package repository

import com.cryptx.cryptx.domain.Network
import com.cryptx.cryptx.repository.NetworkDataSource
import com.cryptx.cryptx.repository.NetworkRepository
import com.cryptx.cryptx.repository.NetworkRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NetworkRepositoryTest {

    private val repository: NetworkRepository = NetworkRepositoryImpl()

    @Test
    fun getCurrentNetwork_returnsMainnetByDefault() = runTest {
        // Reset to default state
        NetworkDataSource.setCurrentNetwork(Network.MAINNET)

        val network = repository.getCurrentNetwork()

        assertEquals(Network.MAINNET, network)
    }

    @Test
    fun setCurrentNetwork_changesNetwork() = runTest {
        repository.setCurrentNetwork(Network.TESTNET)

        val network = repository.getCurrentNetwork()

        assertEquals(Network.TESTNET, network)

        // Reset to default
        repository.setCurrentNetwork(Network.MAINNET)
    }

    @Test
    fun getNetworkConfig_mainnet_returnsConfig() = runTest {
        val config = repository.getNetworkConfig(Network.MAINNET)

        assertNotNull(config)
        assertEquals("Ethereum Mainnet", config.name)
    }

    @Test
    fun getNetworkConfig_testnet_returnsConfig() = runTest {
        val config = repository.getNetworkConfig(Network.TESTNET)

        assertNotNull(config)
        assertEquals("Goerli Testnet", config.name)
    }

    @Test
    fun getAllNetworks_returnsBothNetworks() = runTest {
        val networks = repository.getAllNetworks()

        assertEquals(2, networks.size)
        assert(networks.contains(Network.MAINNET))
        assert(networks.contains(Network.TESTNET))
    }

    @Test
    fun isNetworkAvailable_returnsTrueForMainnet() = runTest {
        repository.setCurrentNetwork(Network.MAINNET)
        val available = repository.isNetworkAvailable()

        assertEquals(true, available)
    }

    @Test
    fun isNetworkAvailable_returnsFalseForTestnet() = runTest {
        repository.setCurrentNetwork(Network.TESTNET)
        val available = repository.isNetworkAvailable()

        assertEquals(false, available)

        // reset
        repository.setCurrentNetwork(Network.MAINNET)
    }
}
