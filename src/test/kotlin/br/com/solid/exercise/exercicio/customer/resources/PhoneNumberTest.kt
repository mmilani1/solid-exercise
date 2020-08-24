package br.com.solid.exercise.exercicio.customer.resources

import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneAliasException
import br.com.solid.exercise.exercicio.customer.exceptions.InvalidPhoneNumberException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PhoneNumberTest {

    @Test
    fun `builds on valid phone number formats`() {
        assertDoesNotThrow {
            PhoneNumber(number = "(00)00000000")
            PhoneNumber(number = "(00)900000000")
            PhoneNumber(number = "(00)0000-0000")
            PhoneNumber(number = "(00)90000-0000")
        }
    }

    @Test
    fun `builds phone number with alias`() {
        assertDoesNotThrow {
            PhoneNumber(alias =  "New Phone", number = "(00)00000000")
        }
    }

    @Test
    fun `throws exception on invalid phone number format`() {
        assertThrows<InvalidPhoneNumberException> {
            PhoneNumber(number = "(0)00-00")
        }
        assertThrows<InvalidPhoneNumberException> {
            PhoneNumber(number = "(00)00000-0000")
        }
        assertThrows<InvalidPhoneNumberException> {
            PhoneNumber(number = "(00)000000000")
        }
    }

    @Test
    fun `throws exception on empty phone numbers`() {
        assertThrows<InvalidPhoneNumberException> {
            PhoneNumber(number = "")
        }
    }

    @Test
    fun `throws exception on empty phone alias`() {
        assertThrows<InvalidPhoneAliasException> {
            PhoneNumber(alias = "", number = "(00)90000-0000")
        }
    }
}