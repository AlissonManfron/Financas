package br.com.alisson.financas.data.local.repository

import br.com.alisson.financas.data.local.dao.TransactionDao
import br.com.alisson.financas.data.mapper.toTransactionEntity
import br.com.alisson.financas.data.mapper.toTransactionList
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.repository.TransactionsRepository

class TransactionsRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionsRepository {

    override suspend fun createTransaction(transaction: Transaction) {
        transactionDao.save(transaction.toTransactionEntity())
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.delete(transaction.toTransactionEntity())
    }

    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAll().toTransactionList()
    }
}