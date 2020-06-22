package br.com.solid.exercise.exercicio.customer.address

data class Address(
    val street: String ,
    val number: String?,
    val neighbourhood: String,
    val additionalInfo: String?,
    val postalCode: String,
    val city: String,
    val state: String
) {

    fun toEntity() = AddressEntity(street, number, neighbourhood, additionalInfo, postalCode, city, state)
}