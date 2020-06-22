package br.com.solid.exercise.exercicio.customer.exceptions

import java.lang.RuntimeException

class CustomerUnderageException: RuntimeException("Customer can't be underage")