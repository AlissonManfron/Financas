package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.data.local.repository.finance.FinanceRepository
import br.com.alisson.financas.domain.model.Finance

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