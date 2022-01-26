package br.com.alisson.financas.data.local.repository.transaction

import br.com.alisson.financas.domain.model.Transaction

interface TransactionsRepository {

    suspend fun createTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    suspend fun getAllTransactions(): List<Transaction>

}