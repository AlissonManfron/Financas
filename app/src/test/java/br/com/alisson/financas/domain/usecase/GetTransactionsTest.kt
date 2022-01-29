package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.TransactionFactory
import br.com.alisson.financas.domain.repository.TransactionsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetTransactionsTest {

    private val repository = mockk<TransactionsRepository>()

    private val getTransactions = GetTransactions(repository)

    @Test
    fun getTransactions_return_with_success() = runBlocking {

        //GIVEN
        coEvery { repository.getAllTransactions() } returns TransactionFactory.transactions

        //WHEN
        val result = getTransactions()

        //THEN
        Assert.assertEquals(result.size, TransactionFactory.transactions.size)

    }

    @Test
    fun getTransactions_return_exception() = runBlocking {

        //GIVEN
        coEvery { repository.getAllTransactions() } throws Exception()

        //THEN
        val result = getTransactions()

        //THEN
        Assert.assertEquals(result.size, 0)

    }

}