package br.com.solid.exercise.exercicio.customer

import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun create(customer: Customer) {
        customerRepository.save(customer.toEntity())
    }
}