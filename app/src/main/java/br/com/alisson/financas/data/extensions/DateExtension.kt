package br.com.alisson.financas.data.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toDateBR(): String = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        .format(this.time)

fun String.toDateBR(): Calendar {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        cal.time = sdf.parse(this)
        return cal
}