package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.address.Address
import br.com.solid.exercise.exercicio.customer.exceptions.CustomerUnderageException
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneNumberException
import br.com.solid.exercise.exercicio.customer.exceptions.PhoneNumberQuantityException
import br.com.solid.exercise.exercicio.customer.exceptions.UniquePhoneNumbersException
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_DATE

data class Customer(
    val name: String,
    val cpf: String,
    val cnh: String,
    val email: String,
    val cityOfBirth: String,
    val phoneNumbers: List<PhoneNumber>,
    val birthDate: BirthDate,
    val address: Address
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
        cnh = cnh,
        birthDate = birthDate.value,
        cityOfBirth = cityOfBirth,
        address = address.toEntity()
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

data class BirthDate(
    val value: LocalDate
) {
    companion object {
        const val minimumAge = 18
    }

    init {
        isNotUnderage()
    }

    override fun toString(): String = value.format(ISO_DATE)

    private fun isNotUnderage() = (value.until(LocalDate.now())).years > 18  || throw CustomerUnderageException()
}