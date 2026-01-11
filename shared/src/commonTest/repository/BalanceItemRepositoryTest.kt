package repository

import com.cryptx.cryptx.repository.BalanceItemDataSource
import com.cryptx.cryptx.repository.BalanceItemRepository
import com.cryptx.cryptx.repository.BalanceItemRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BalanceItemRepositoryTest {

    private val repository: BalanceItemRepository = BalanceItemRepositoryImpl()

    @Test
    fun getAllBalances_returnsAllItems() = runTest {
        val balances = repository.getAllBalances()

        assertEquals(5, balances.size)
    }

    @Test
    fun getAllBalances_containsBTC() = runTest {
        val balances = repository.getAllBalances()

        val btc = balances.find { it.symbol == "BTC" }
        assertNotNull(btc)
        assertEquals("Bitcoin", btc.name)
    }

    @Test
    fun getBalanceBySymbol_existingSymbol_returnsItem() = runTest {
        val eth = repository.getBalanceBySymbol("ETH")

        assertNotNull(eth)
        assertEquals("Ethereum", eth.name)
        assertEquals(10.0, eth.quantity)
    }

    @Test
    fun getBalanceBySymbol_nonExistingSymbol_returnsNull() = runTest {
        val unknown = repository.getBalanceBySymbol("UNKNOWN")

        assertNull(unknown)
    }

    @Test
    fun balanceItem_totalValue_calculatedCorrectly() = runTest {
        val btc = repository.getBalanceBySymbol("BTC")

        assertNotNull(btc)
        assertEquals(btc.quantity * btc.price, btc.totalValue)
    }
}
