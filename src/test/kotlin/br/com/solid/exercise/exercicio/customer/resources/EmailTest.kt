package br.com.solid.exercise.exercicio.customer.resources

import br.com.solid.exercise.exercicio.customer.exceptions.InvalidEmailException
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneAliasException
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneNumberException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EmailTest {

    @Test
    fun `builds on valid email`() {
        assertDoesNotThrow {
            Email("email@email.com")
            Email("email.email@email.io")
            Email("Email@email.co.uk")
            Email("Email_email@gmail.com")
        }
    }

    @Test
    fun `throws exception on invalid email`() {
        assertThrows<InvalidEmailException> {
            Email("")
        }
        assertThrows<InvalidEmailException> {
            Email("@email")
        }
        assertThrows<InvalidEmailException> {
            Email("email")
        }
        assertThrows<InvalidEmailException> {
            Email("email@email.")
        }
        assertThrows<InvalidEmailException> {
            Email("$@email.com")
        }
    }
}