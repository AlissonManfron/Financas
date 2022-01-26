package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepository
import br.com.alisson.financas.domain.model.Transaction

class CreateTransaction(
    private val repository: TransactionsRepository
): CreateTransactionUseCase {

    override suspend fun invoke(transaction: Transaction) {
        repository.createTransaction(transaction)
    }
}

interface CreateTransactionUseCase {
    suspend operator fun invoke(transaction: Transaction)
}