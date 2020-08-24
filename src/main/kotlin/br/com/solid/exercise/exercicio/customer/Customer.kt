package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.address.Address
import br.com.solid.exercise.exercicio.customer.exceptions.CustomerUnderageException
import br.com.solid.exercise.exercicio.customer.exceptions.PhoneNumberQuantityException
import br.com.solid.exercise.exercicio.customer.exceptions.UniquePhoneNumbersException
import br.com.solid.exercise.exercicio.customer.resources.BirthDate
import br.com.solid.exercise.exercicio.customer.resources.CPF
import br.com.solid.exercise.exercicio.customer.resources.Email
import br.com.solid.exercise.exercicio.customer.resources.PhoneNumber

data class Customer(
    val name: String,
    val cpf: CPF,
    val cnh: String,
    val email: Email,
    val cityOfBirth: String,
    private val phoneNumbers: List<PhoneNumber>,
    val birthDate: BirthDate,
    val address: Address
) {

    private val _phoneNumbers: MutableList<PhoneNumber> = mutableListOf()

    companion object {
        const val MINIMUM_PHONE_NUMBERS = 2
    }

    init {
        phoneNumbers.forEach { addPhoneNumber(it) }
        isNotUnderage()
        hasMinimumPhoneNumbers()
    }

    fun addPhoneNumber(phoneNumber: PhoneNumber) {
        if (_phoneNumbers.contains(phoneNumber)) throw UniquePhoneNumbersException()
        _phoneNumbers.add(phoneNumber)
    }

    fun toEntity() = CustomerEntity(
        name = name,
        cpf = cpf.toString(),
        email = email.toString(),
        cnh = cnh,
        birthDate = birthDate.toLocalDate(),
        cityOfBirth = cityOfBirth,
        address = address.toEntity()
    ).also { customerEntity ->
        customerEntity.phoneNumbers = _phoneNumbers.map { it.toEntity(customer = customerEntity) }
    }

    private fun isNotUnderage() = birthDate.isUnderage() && throw CustomerUnderageException()
    private fun hasMinimumPhoneNumbers() = _phoneNumbers.size >= MINIMUM_PHONE_NUMBERS || throw PhoneNumberQuantityException()
}
