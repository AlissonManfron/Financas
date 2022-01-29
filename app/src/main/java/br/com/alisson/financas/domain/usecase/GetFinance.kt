package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.repository.FinanceRepository

class GetFinance(
    private val repository: FinanceRepository
) : GetFinanceUseCase {

    override suspend fun invoke(): Finance {
        return repository.getFinance()
    }
}

interface GetFinanceUseCase {
    suspend operator fun invoke(): Finance
}