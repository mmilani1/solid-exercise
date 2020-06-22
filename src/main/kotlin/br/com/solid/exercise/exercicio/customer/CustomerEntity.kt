package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.address.AddressEntity
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.AUTO

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

    @field:Column(name = "cnh", nullable = false)
    val cnh: String,

    @field:Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @field:Column(name = "email", nullable = false)
    val email: String,

    @field:Column(name = "city_of_birth", nullable = false)
    val cityOfBirth: String,

    @Embedded
    val address: AddressEntity
) {

    @OneToMany(mappedBy = "customer", targetEntity = PhoneNumberEntity::class, cascade = [ALL], orphanRemoval = true, fetch = LAZY)
    lateinit var phoneNumbers: List<PhoneNumberEntity>

    fun toDomain() = Customer(
        name = name,
        cpf = cpf,
        email = email,
        cnh = cnh,
        cityOfBirth = cityOfBirth,
        phoneNumbers = phoneNumbers.map { it.toDomain(this) },
        birthDate = BirthDate(birthDate),
        address = address.toDomain()
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
