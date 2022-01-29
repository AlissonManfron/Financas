package br.com.alisson.financas.domain

import br.com.alisson.financas.data.util.FinanceCategory
import br.com.alisson.financas.domain.model.Transaction
import java.util.*

object TransactionFactory {

    val transactions = listOf(
        Transaction(
            0,
            5000.00,
            "Salário",
            FinanceCategory.REVENUE.name,
            Calendar.getInstance()
        ),
        Transaction(
            1,
            500.00,
            "Renda extra",
            FinanceCategory.REVENUE.name,
            Calendar.getInstance()
        ),
        Transaction(
            2,
            180.00,
            "Conta de luz",
            FinanceCategory.EXPENSE.name,
            Calendar.getInstance()
        ),
        Transaction(
            3,
            50.00,
            "Tim",
            FinanceCategory.EXPENSE.name,
            Calendar.getInstance()
        )
    )
}