package br.com.alisson.financas.domain.model

import java.util.*

data class Transaction(
    var id: Int?,
    var price: Double,
    var description: String,
    var category: String,
    var date: Calendar
)