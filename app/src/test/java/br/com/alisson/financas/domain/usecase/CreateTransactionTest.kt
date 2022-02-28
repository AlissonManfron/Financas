package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.data.util.FinanceCategory
import br.com.alisson.financas.domain.TransactionFactory
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.repository.TransactionsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import java.util.*

class CreateTransactionTest {

    private val repository = mockk<TransactionsRepository>()

    private val createTransaction = CreateTransaction(repository)

    @After
    fun after() {
        TransactionFactory.removeLast()
    }

    @Test
    fun createTransaction_revenue_with_success() = runBlocking {

        //GIVEN
        val transaction = Transaction(
            4,
            7000.0,
            "Salário",
            FinanceCategory.REVENUE.name,
            Calendar.getInstance()
        )
        coEvery {
            repository.createTransaction(transaction)
        } returns TransactionFactory.addTransacion(transaction)

        //WHEN
        createTransaction(transaction)

        //THEN
        Assert.assertEquals(5, TransactionFactory.transactions.size)
        Assert.assertEquals(4, TransactionFactory.transactions.last().id)
    }

    @Test
    fun createTransaction_expense_with_success() = runBlocking {

        //GIVEN
        val transaction = Transaction(
            4,
            260.0,
            "Conta de água",
            FinanceCategory.EXPENSE.name,
            Calendar.getInstance()
        )
        coEvery {
            repository.createTransaction(transaction)
        } returns TransactionFactory.addTransacion(transaction)

        //WHEN
        createTransaction(transaction)

        //THEN
        Assert.assertEquals(5, TransactionFactory.transactions.size)
        Assert.assertEquals(4, TransactionFactory.transactions.last().id)
    }
}