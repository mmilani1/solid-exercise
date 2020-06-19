package br.com.solid.exercise.exercicio.customer

import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.EAGER
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.AUTO
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "customer")
data class CustomerEntity(
    @field:Id
    @field:Column(name = "id")
    @field:GeneratedValue(strategy = AUTO)
    val id: UUID = UUID.randomUUID(),

    @field:Column(name = "name", nullable = false)
    val name: String,

    @field:Column(name = "cpf", nullable = false)
    val cpf: String,

    @field:Column(name = "email", nullable = false)
    val email: String,

    @field:Column(name = "city_of_birth", nullable = false)
    val cityOfBirth: String

) {

    @OneToMany(mappedBy = "customer", targetEntity = PhoneNumberEntity::class, cascade = [ALL], orphanRemoval = true, fetch = LAZY)
    lateinit var phoneNumbers: List<PhoneNumberEntity>

    fun toDomain() = Customer(
        name,
        cpf,
        email,
        cityOfBirth,
        phoneNumbers.map { it.toDomain(this) }
    )
}

@Entity
@Table(name = "phone_number")
data class PhoneNumberEntity(
    @field:Column(name = "alias", nullable = true)
    val alias: String?,

    @field:Column(name = "number", nullable = false)
    val number: String
) {

    @field:Id
    @field:Column(name = "id")
    @field:GeneratedValue(strategy = AUTO)
    lateinit var id: UUID

    @field:ManyToOne(cascade = [ALL], fetch = LAZY)
    lateinit var customer: CustomerEntity

    fun toDomain(customer: CustomerEntity) = PhoneNumber(alias, number)
}
