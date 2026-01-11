package repository

import com.cryptx.cryptx.repository.WalletRepository
import com.cryptx.cryptx.repository.WalletRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WalletRepositoryTest {

    private val repository: WalletRepository = WalletRepositoryImpl()

    @Test
    fun getAddress_returnsValidAddress() = runTest {
        val address = repository.getAddress()

        assertNotNull(address)
        assertTrue(address.startsWith("0x"))
    }

    @Test
    fun getWallet_returnsWalletWithBalances() = runTest {
        val wallet = repository.getWallet()

        assertNotNull(wallet)
        assertEquals(repository.getAddress(), wallet.address)
        assertTrue(wallet.balances.isNotEmpty())
    }

    @Test
    fun getWallet_totalBalanceIsPositive() = runTest {
        val wallet = repository.getWallet()

        assertTrue(wallet.totalBalance > 0)
    }

    @Test
    fun getBalances_returnsAllBalances() = runTest {
        val balances = repository.getBalances()

        assertEquals(5, balances.size)
    }

    @Test
    fun getBalanceBySymbol_existingSymbol_returnsBalance() = runTest {
        val btc = repository.getBalanceBySymbol("BTC")

        assertNotNull(btc)
        assertEquals("Bitcoin", btc.name)
    }

    @Test
    fun getBalanceBySymbol_nonExistingSymbol_returnsNull() = runTest {
        val unknown = repository.getBalanceBySymbol("UNKNOWN")

        assertNull(unknown)
    }

    @Test
    fun wallet_containsExpectedAssets() = runTest {
        val wallet = repository.getWallet()
        val symbols = wallet.balances.map { it.symbol }

        assertTrue(symbols.contains("BTC"))
        assertTrue(symbols.contains("ETH"))
        assertTrue(symbols.contains("SOL"))
        assertTrue(symbols.contains("USDT"))
        assertTrue(symbols.contains("ADA"))
    }

    @Test
    fun getPriceHistory_returnsHistory() = runTest {
        val history = repository.getPriceHistory("BTC")

        assertEquals("BTC", history.symbol)
        assertTrue(history.candles.isNotEmpty())
    }

    @Test
    fun sendTransaction_addsTransactionAndReturnsResult() = runTest {
        val to = "0xfeedface"
        val amount = 0.1
        val symbol = "BTC"

        val result = repository.sendTransaction(to, amount, symbol)

        assert(result.isSuccess)
        val tx = result.getOrNull()
        assertNotNull(tx)
        assertEquals(to, tx.to)
        assertEquals(amount, tx.amount)
        assertEquals(symbol, tx.symbol)
    }
}
