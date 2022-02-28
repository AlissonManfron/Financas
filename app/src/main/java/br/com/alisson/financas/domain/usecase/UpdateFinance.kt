package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.repository.FinanceRepository

class UpdateFinance(
    private val repository: FinanceRepository
) : UpdateFinanceUseCase {

    override suspend fun invoke(finance: Finance) {
        repository.createFinance(finance)
    }
}

interface UpdateFinanceUseCase {
    suspend operator fun invoke(finance: Finance)
}