package br.com.solid.exercise.exercicio.customer.address

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class AddressEntity(
    @field:Column(name = "street", nullable = false)
    val street: String,

    @field:Column(name = "number", nullable = true)
    val number: String?,

    @field:Column(name = "neighbourhood", nullable = false)
    val neighbourhood: String,

    @field:Column(name = "additional_info", nullable = true)
    val additionalInfo: String?,

    @field:Column(name = "postal_code", nullable = false)
    val postalCode: String,

    @field:Column(name = "city", nullable = false)
    val city: String,

    @field:Column(name = "state", nullable = false)
    val state: String
) {

    fun toDomain() = Address(street, number, neighbourhood, additionalInfo, postalCode, city, state)
}