package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.repository.TransactionsRepository

class GetTransactions(
    private val repository: TransactionsRepository
): GetTransactionsUseCase {

    override suspend fun invoke(): List<Transaction> = try {
        repository.getAllTransactions().reversed()
    } catch (ex: Exception) {
        listOf()
    }
}

interface GetTransactionsUseCase {
    suspend operator fun invoke(): List<Transaction>
}