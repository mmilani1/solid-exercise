package br.com.solid.exercise.exercicio.customer.resources

import br.com.solid.exercise.exercicio.customer.CustomerEntity
import br.com.solid.exercise.exercicio.customer.PhoneNumberEntity
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneAliasException
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneNumberException

data class PhoneNumber(
    val alias: String? = null,
    val number: String
) {
    companion object {
        const val phoneNumberPattern = "\\((\\d{2})\\)(9?\\d{4}-?\\d{4})"
    }

    init {
        validateNumber()
        validateAlias()
    }

    fun toEntity(customer: CustomerEntity) = PhoneNumberEntity(alias = alias, number = number).also { it.customer = customer }

    private fun validateNumber() = phoneNumberPattern.toRegex().matches(number) || throw InvalidPhoneNumberException()
    private fun validateAlias() =
        when (alias?.isBlank()) {
            true -> throw InvalidPhoneAliasException()
            else -> true
        }

}