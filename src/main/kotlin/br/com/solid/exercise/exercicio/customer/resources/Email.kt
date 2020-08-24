package br.com.solid.exercise.exercicio.customer.resources

import br.com.solid.exercise.exercicio.customer.exceptions.InvalidEmailException

data class Email(
    private val value: String
) {
    companion object {
        const val emailPattern = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
    }

    init {
        validateEmail()
    }

    override fun toString() = value
    private fun validateEmail() = emailPattern.toRegex().matches(value) || throw InvalidEmailException()
}