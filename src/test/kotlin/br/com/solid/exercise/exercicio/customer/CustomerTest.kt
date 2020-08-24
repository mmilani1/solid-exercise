package br.com.solid.exercise.exercicio.customer

import br.com.solid.exercise.exercicio.customer.address.Address
import br.com.solid.exercise.exercicio.customer.exceptions.CustomerUnderageException
import br.com.solid.exercise.exercicio.customer.exceptions.PhoneNumberQuantityException
import br.com.solid.exercise.exercicio.customer.exceptions.UniquePhoneNumbersException
import br.com.solid.exercise.exercicio.customer.resources.BirthDate
import br.com.solid.exercise.exercicio.customer.resources.CPF
import br.com.solid.exercise.exercicio.customer.resources.Email
import br.com.solid.exercise.exercicio.customer.resources.PhoneNumber
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate


class CustomerTest {

    @Nested
    inner class WhenCustomerIsValid {

        @Test
        fun `Customer is built correctly`() {
            assertDoesNotThrow {
                buildCustomer()
            }
        }
    }

    @Nested
    inner class WhenCustomerIsNotValid {

        @Test
        fun `throws when customer is underage`() {
            assertThrows<CustomerUnderageException> {
                buildCustomer(birthDate = BirthDate(LocalDate.now()))
            }
        }

        @Test
        fun `throws when does not have at least two phone numbers`() {
            assertThrows<PhoneNumberQuantityException> {
                buildCustomer(phoneNumbers = listOf())
            }
        }

        @Test
        fun `throws when phone numbers are equal`() {
            assertThrows<UniquePhoneNumbersException> {
                buildCustomer(phoneNumbers = listOf(
                    PhoneNumber(number = "(11)12345678"),
                    PhoneNumber(number = "(11)12345678")
                ))
            }
        }
    }

    private fun buildCustomer(
        birthDate: BirthDate? = null,
        phoneNumbers: List<PhoneNumber>? = null
    ) = Customer(
        name = "Name",
        cpf = CPF("98765432100"),
        birthDate = birthDate ?: BirthDate(LocalDate.of(2000, 10, 10)),
        cityOfBirth = "City",
        cnh = "12345678900",
        email = Email("email@email.com"),
        phoneNumbers = phoneNumbers ?: listOf(
            PhoneNumber(number = "(11)12345678"),
            PhoneNumber(number = "(11)912345678")
        ),
        address = Address(
            street = "Street",
            city = "City",
            postalCode = "09123-456",
            state = "State",
            neighbourhood = "Neighbourhood"
        )
    )
}