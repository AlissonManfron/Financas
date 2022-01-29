package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.repository.TransactionsRepository

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