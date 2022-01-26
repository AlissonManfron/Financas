package br.com.alisson.financas.domain.model

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
}