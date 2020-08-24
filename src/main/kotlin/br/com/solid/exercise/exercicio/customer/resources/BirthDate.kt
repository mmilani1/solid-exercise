package br.com.solid.exercise.exercicio.customer.resources

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class BirthDate(
    private val value: LocalDate
) {

    companion object {
        const val minimumAge = 18
    }

    fun toLocalDate() = value
    override fun toString(): String = value.format(DateTimeFormatter.ISO_DATE)
    fun isUnderage() = value.isAfter(underageDateLimit())

    private fun underageDateLimit() = LocalDate.now().minusYears(minimumAge.toLong())
}