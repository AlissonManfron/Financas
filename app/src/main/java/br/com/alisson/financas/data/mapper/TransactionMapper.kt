package br.com.alisson.financas.data.mapper

import br.com.alisson.financas.data.local.entity.TransactionEntity
import br.com.alisson.financas.data.extensions.toDateBR
import br.com.alisson.financas.domain.model.Transaction


fun Transaction.toTransactionEntity(): TransactionEntity {
    return with(this) {
        TransactionEntity(
            id = this.id ?: 0,
            price = this.price,
            description = this.description,
            category = this.category,
            date = this.date.toDateBR()
        )
    }
}

fun TransactionEntity.toTransaction(): Transaction {
    return with(this) {
        Transaction(
            id = this.id,
            price = this.price,
            description = this.description,
            category = this.category,
            date = this.date.toDateBR()
        )
    }
}

fun List<TransactionEntity>.toTransactionList(): List<Transaction> = with(this) {
    val transactions = mutableListOf<Transaction>()

    this.toList().forEach {
        val st = it.toTransaction()
        transactions += st
    }

    return transactions
}