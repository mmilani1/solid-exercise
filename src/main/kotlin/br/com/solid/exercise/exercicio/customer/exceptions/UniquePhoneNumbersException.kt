package br.com.solid.exercise.exercicio.customer.exceptions

import java.lang.RuntimeException

class UniquePhoneNumbersException : RuntimeException("All phone numbers must be unique")
