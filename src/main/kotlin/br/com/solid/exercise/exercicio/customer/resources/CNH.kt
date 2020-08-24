package br.com.solid.exercise.exercicio.customer.resources

data class CNH(
    private val value: String
) {
    companion object {
        const val cnhPattern = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"
    }

    init {
        validateCnhFormat()
        validateCnhValue()
    }

    override fun toString() = value.replace(Regex("\\D"), "")
    private fun validateCnhFormat() = cnhPattern.toRegex().matches(value) || throw InvalidCnhFormatException()
    private fun validateCnhValue() = {}

}