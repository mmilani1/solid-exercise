package br.com.solid.exercise.exercicio.customer

import org.hibernate.validator.constraints.br.CPF
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CustomerRequest(
    @Max(180)
    @field:NotBlank
    val name: String = "",

    @field:CPF
    @field:NotBlank
    val cpf: String = "",

    @field:Email
    @field:NotBlank
    val email: String = "",

    @field:NotBlank
    val cityOfBirth: String = "",

    @Valid
    @NotNull
    val phoneNumbers: List<PhoneNumberRequest>
) {

    fun toCustomer() = Customer(
        name = name,
        cpf = cpf,
        email = email,
        cityOfBirth = cityOfBirth,
        phoneNumbers = phoneNumbers.map { it.toPhoneNumber() }
    )

    data class PhoneNumberRequest (
        val alias: String? = null,

        @NotBlank
        val number: String = ""
    ) {

        fun toPhoneNumber() = PhoneNumber (
            alias, number
        )
    }
}
