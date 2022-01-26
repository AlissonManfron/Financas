package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.data.local.repository.finance.FinanceRepository
import br.com.alisson.financas.data.util.FinanceCategory
import br.com.alisson.financas.domain.model.Transaction

class UpdateFinance(
    private val repository: FinanceRepository
) : UpdateFinanceUseCase {

    override suspend fun invoke(transaction: Transaction) {
        val finance = repository.getFinance()
        when (FinanceCategory.valueOf(transaction.category)) {
            FinanceCategory.REVENUE -> finance.revenue += transaction.price
            FinanceCategory.EXPENSE -> finance.expense += transaction.price
        }
        finance.total = finance.revenue - finance.expense

        repository.createFinance(finance)
    }
}

interface UpdateFinanceUseCase {
    suspend operator fun invoke(transaction: Transaction)
}