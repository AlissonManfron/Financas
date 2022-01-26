package br.com.alisson.financas.data.mapper

import br.com.alisson.financas.data.local.entity.FinanceEntity
import br.com.alisson.financas.domain.model.Finance

fun Finance.toFinanceEntity(): FinanceEntity {
    return with(this) {
        FinanceEntity(
            id = this.id ?: 0,
            revenue = this.revenue,
            expense = this.expense,
            total = this.total
        )
    }
}

fun FinanceEntity.toFinance(): Finance {
    return with(this) {
        Finance(
            id = this.id,
            revenue = this.revenue,
            expense = this.expense,
            total = this.total
        )
    }
}