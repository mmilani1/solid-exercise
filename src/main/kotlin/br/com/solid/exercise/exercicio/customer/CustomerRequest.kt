package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.address.AddressRequest
import br.com.solid.exercise.exercicio.customer.resources.BirthDate
import br.com.solid.exercise.exercicio.customer.resources.CPF
import br.com.solid.exercise.exercicio.customer.resources.Email
import br.com.solid.exercise.exercicio.customer.resources.PhoneNumber
import org.hibernate.validator.constraints.br.CPF as CPFPattern
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import javax.validation.constraints.Email as EmailPattern

data class CustomerRequest(
    @field:Size(max = 180)
    @field:NotBlank
    val name: String = "",

    @field:CPFPattern
    @field:NotBlank
    val cpf: String = "",

    @field:Size(max = 20)
    @field:NotBlank
    val cnh: String = "",

    @field:EmailPattern
    @field:NotBlank
    val email: String = "",

    @field:NotBlank
    val cityOfBirth: String = "",

    @field:DateTimeFormat(iso = ISO.DATE)
    @field:NotNull
    val birthDate: LocalDate? = null,

    @field:Valid
    @field:NotNull
    val phoneNumbers: List<PhoneNumberRequest>,

    @field:Valid
    @field:NotNull
    val address: AddressRequest
) {

    fun toCustomer() = Customer(
        name = name,
        cpf = CPF(cpf),
        cnh = cnh,
        email = Email(email),
        birthDate = BirthDate(birthDate!!),
        cityOfBirth = cityOfBirth,
        phoneNumbers = phoneNumbers.map { it.toPhoneNumber() },
        address = address.toAddress()
    )

    data class PhoneNumberRequest(
        val alias: String? = null,

        @NotBlank
        val number: String = ""
    ) {

        fun toPhoneNumber() = PhoneNumber(
            alias, number
        )
    }
}
