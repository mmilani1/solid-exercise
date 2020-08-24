package br.com.solid.exercise.exercicio.customer.resources

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BirthDateTest {

    companion object {
        const val today = "2020-08-20"
        const val underageBirthDate = "2002-08-21"
        const val notUnderageBirthDate = "2002-08-20"
    }

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns LocalDate.parse(today)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun isUnderage() {
        val underage = LocalDate.parse(underageBirthDate)
        val notUnderage = LocalDate.parse(notUnderageBirthDate)

        assertTrue{ BirthDate(LocalDate.now()).isUnderage() }
        assertTrue{ BirthDate(underage).isUnderage() }
        assertFalse{ BirthDate(notUnderage).isUnderage() }
    }

    @Test
    fun `toString returns ISO format`() {
        assertTrue {
            BirthDate(LocalDate.parse(today)).toString() == today
        }
    }
}