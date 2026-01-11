package repository

import com.cryptx.cryptx.repository.SymbolPriceRepository
import com.cryptx.cryptx.repository.SymbolPriceRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SymbolPriceRepositoryTest {

    private val repository: SymbolPriceRepository = SymbolPriceRepositoryImpl()

    @Test
    fun getAllPrices_returnsAllPrices() = runTest {
        val prices = repository.getAllPrices()

        assertEquals(5, prices.size)
    }

    @Test
    fun getPriceBySymbol_existingSymbol_returnsPrice() = runTest {
        val btcPrice = repository.getPriceBySymbol("BTC")

        assertNotNull(btcPrice)
        assertEquals("BTC", btcPrice.symbol)
        assertEquals(42000.0, btcPrice.price)
    }

    @Test
    fun getPriceBySymbol_nonExistingSymbol_returnsNull() = runTest {
        val unknown = repository.getPriceBySymbol("UNKNOWN")

        assertNull(unknown)
    }

    @Test
    fun getPriceHistory_existingSymbol_returnsHistory() = runTest {
        val btcHistory = repository.getPriceHistory("BTC")

        assertNotNull(btcHistory)
        assertEquals("BTC", btcHistory.symbol)
        assertTrue(btcHistory.candles.isNotEmpty())
    }

    @Test
    fun getPriceHistory_candlesHaveCorrectStructure() = runTest {
        val ethHistory = repository.getPriceHistory("ETH")

        assertNotNull(ethHistory)
        val firstCandle = ethHistory.candles.first()
        assertTrue(firstCandle.timestamp > 0)
        assertTrue(firstCandle.open > 0)
        assertTrue(firstCandle.high >= firstCandle.low)
        assertTrue(firstCandle.close > 0)
    }

    @Test
    fun getPriceHistory_nonExistingSymbol_returnsNull() = runTest {
        val unknown = repository.getPriceHistory("UNKNOWN")

        assertNull(unknown)
    }
}
