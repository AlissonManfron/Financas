package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.FinanceFactory
import br.com.alisson.financas.domain.TransactionFactory
import br.com.alisson.financas.domain.repository.FinanceRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test

class UpdateFinanceTest {

    private val repository = mockk<FinanceRepository>()

    private val updateFinance = UpdateFinance(repository)

    @After
    fun after() {
        FinanceFactory.reset()
    }

    @Test
    fun updateFinance_with_revenue_transaction() = runBlocking {

        //GIVEN
        val transaction = TransactionFactory.transactions.first()
        val finance = FinanceFactory.finance
        coEvery { repository.createFinance(finance) } returns FinanceFactory.updateFinance(transaction)

        //WHEN
        updateFinance(finance)

        //THEN
        Assert.assertEquals(0, FinanceFactory.finance.id)
        Assert.assertEquals(5000.0, FinanceFactory.finance.total, 0.0001)
    }

    @Test
    fun updateFinance_with_expense_transaction() = runBlocking {

        //GIVEN
        val finance = FinanceFactory.finance
        finance.update(TransactionFactory.transactions[0])

        val transaction = TransactionFactory.transactions[2]
        coEvery { repository.createFinance(finance) } returns FinanceFactory.updateFinance(transaction)

        //WHEN
        updateFinance(finance)

        //THEN
        Assert.assertEquals(0, FinanceFactory.finance.id)
        Assert.assertEquals(4820.0, FinanceFactory.finance.total, 0.0001)
        Assert.assertEquals(180.0, FinanceFactory.finance.expense, 0.0001)
    }
}