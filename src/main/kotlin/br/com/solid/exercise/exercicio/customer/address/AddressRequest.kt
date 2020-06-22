package br.com.solid.exercise.exercicio.customer.address

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AddressRequest(
    @field:Size(max = 120)
    @field:NotBlank
    val street: String = "",

    @field:Size(max = 6)
    val number: String? = null,

    @field:Size(max = 60)
    @field:NotBlank
    val neighbourhood: String = "",

    @field:Size(max = 100)
    val additionalInfo: String? = null,

    @field:Size(max = 8)
    @field:NotBlank
    val postalCode: String = "",

    @field:NotBlank
    val city: String = "",

    @field:NotBlank
    val state: String = ""
) {

    fun toAddress() = Address(street, number, neighbourhood, additionalInfo, postalCode, city, state)
}
