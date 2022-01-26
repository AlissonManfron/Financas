package br.com.alisson.financas.domain.usecase

import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepository
import br.com.alisson.financas.domain.model.Transaction
import java.lang.Exception

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