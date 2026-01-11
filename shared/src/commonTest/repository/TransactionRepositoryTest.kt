package repository

import com.cryptx.cryptx.domain.TransactionStatus
import com.cryptx.cryptx.repository.TransactionRepository
import com.cryptx.cryptx.repository.TransactionRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TransactionRepositoryTest {

    private val repository: TransactionRepository = TransactionRepositoryImpl()

    @Test
    fun getAllTransactions_returnsTransactions() = runTest {
        val transactions = repository.getAllTransactions()

        assertTrue(transactions.isNotEmpty())
    }

    @Test
    fun getTransactionById_existingId_returnsTransaction() = runTest {
        val transaction = repository.getTransactionById("tx_001")

        assertNotNull(transaction)
        assertEquals("tx_001", transaction.id)
        assertEquals("BTC", transaction.symbol)
    }

    @Test
    fun getTransactionById_nonExistingId_returnsNull() = runTest {
        val transaction = repository.getTransactionById("tx_999")

        assertNull(transaction)
    }

    @Test
    fun getTransactionsByAddress_returnsMatchingTransactions() = runTest {
        val address = "0x1234567890abcdef1234567890abcdef12345678"
        val transactions = repository.getTransactionsByAddress(address)

        assertTrue(transactions.isNotEmpty())
        transactions.forEach { tx ->
            assertTrue(tx.from == address || tx.to == address)
        }
    }

    @Test
    fun getTransactionsByStatus_success_returnsSuccessfulTransactions() = runTest {
        val transactions = repository.getTransactionsByStatus(TransactionStatus.SUCCESS)

        assertTrue(transactions.isNotEmpty())
        transactions.forEach { tx ->
            assertEquals(TransactionStatus.SUCCESS, tx.status)
        }
    }

    @Test
    fun getTransactionsByStatus_pending_returnsPendingTransactions() = runTest {
        val transactions = repository.getTransactionsByStatus(TransactionStatus.PENDING)

        assertTrue(transactions.isNotEmpty())
        transactions.forEach { tx ->
            assertEquals(TransactionStatus.PENDING, tx.status)
        }
    }

    @Test
    fun addTransaction_createsNewTransaction() = runTest {
        val from = "0xabc123"
        val to = "0xdef456"
        val amount = 1.5
        val symbol = "ETH"

        val newTx = repository.addTransaction(from, to, amount, symbol)

        assertNotNull(newTx)
        assertEquals(from, newTx.from)
        assertEquals(to, newTx.to)
        assertEquals(amount, newTx.amount)
        assertEquals(symbol, newTx.symbol)
        assertEquals(TransactionStatus.PENDING, newTx.status)
    }
}
