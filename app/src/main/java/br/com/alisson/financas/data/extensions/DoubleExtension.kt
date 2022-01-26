package br.com.alisson.financas.data.extensions

import java.text.NumberFormat
import java.util.*

fun Double.toRS() : String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("BRL")
    return format.format(this)
}

fun Double.toExpenseRS() : String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("BRL")
    return "- " + format.format(this)
}

fun Double.toRevenueRS() : String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("BRL")
    return "+ " + format.format(this)
}