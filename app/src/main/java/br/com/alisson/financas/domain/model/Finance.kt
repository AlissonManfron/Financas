package br.com.alisson.financas.domain.model

import br.com.alisson.financas.data.extensions.toExpenseRS
import br.com.alisson.financas.data.extensions.toRS
import br.com.alisson.financas.data.extensions.toRevenueRS

class Finance(
    var id: Long,
    var revenue: Double,
    var expense: Double,
    var total: Double) {

    constructor(id: Long) : this(
        id,
        0.0,
        0.0,
        0.0
    )

    fun getRevenueRS() = this.revenue.toRevenueRS()

    fun getExpenseRS() = this.expense.toExpenseRS()

    fun getTotalRS() = this.total.toRS()
}