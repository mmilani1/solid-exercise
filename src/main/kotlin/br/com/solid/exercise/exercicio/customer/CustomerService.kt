package br.com.solid.exercise.exercicio.customer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun create(customer: Customer) {
        customerRepository.save(customer.toEntity())
    }
}