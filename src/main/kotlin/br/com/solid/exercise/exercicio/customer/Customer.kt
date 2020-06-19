package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneNumberException
import br.com.solid.exercise.exercicio.customer.exceptions.PhoneNumberQuantityException
import br.com.solid.exercise.exercicio.customer.exceptions.UniquePhoneNumbersException

data class Customer (
    val name: String,
    val cpf: String,
    val email: String,
    val cityOfBirth: String,
    val phoneNumbers: List<PhoneNumber>
) {

    companion object {
        const val MINIMUM_PHONE_NUMBERS = 2
    }

    init {
        hasMinimumPhoneNumbers()
        hasUniquePhoneNumbers()
    }

    fun toEntity() = CustomerEntity(
        name = name,
        cpf = cpf,
        email = email,
        cityOfBirth = cityOfBirth
    ).also { customerEntity ->
        customerEntity.phoneNumbers = phoneNumbers.map { it.toEntity(customer = customerEntity) }
    }

    private fun hasMinimumPhoneNumbers() = phoneNumbers.size >= MINIMUM_PHONE_NUMBERS || throw PhoneNumberQuantityException()
    private fun hasUniquePhoneNumbers() = phoneNumbers.size >= MINIMUM_PHONE_NUMBERS || throw UniquePhoneNumbersException()
}

data class PhoneNumber (
    val alias: String?,
    val number: String
) {
    companion object {
        const val phoneNumberPattern = "\\((\\d{2})\\)(\\d{4,5}\\d{4})"
    }

    init {
        validateNumber()
    }

    fun toEntity(customer: CustomerEntity) = PhoneNumberEntity(alias = alias, number = number).also { it.customer = customer }

    private fun validateNumber() = phoneNumberPattern.toRegex().matches(number) || throw InvalidPhoneNumberException()
}