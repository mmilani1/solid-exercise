package br.com.solid.exercise.exercicio.customer.resources

import br.com.solid.exercise.exercicio.customer.exceptions.InvalidCpfFormatException

data class CPF(
    private val value: String
) {
    companion object {
        const val cpfPattern = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"
    }

    init {
        validateCpfFormat()
        validateCpfValue()
    }

    override fun toString() = value.replace(Regex("\\D"), "")
    private fun validateCpfFormat() = cpfPattern.toRegex().matches(value) || throw InvalidCpfFormatException()
    private fun validateCpfValue() = {}
}