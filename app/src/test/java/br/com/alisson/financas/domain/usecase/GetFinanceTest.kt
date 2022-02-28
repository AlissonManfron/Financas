package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.repository.FinanceRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetFinanceTest {

    private val repository = mockk<FinanceRepository>()

    private val getFinance = GetFinance(repository)

    @Test
    fun getFinance_with_empty_values() = runBlocking {

        //GIVEN
        coEvery { repository.getFinance() } returns Finance(0)

        //WHEN
        val result = getFinance()

        //THEN
        Assert.assertEquals(0, result.id)
    }
}