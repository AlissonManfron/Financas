package br.com.alisson.financas.domain

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.model.Transaction

object FinanceFactory {

    var finance = Finance(0)


    fun updateFinance(transaction: Transaction) {
        finance.update(transaction)
    }

    fun reset() {
        finance = Finance(0)
    }
}