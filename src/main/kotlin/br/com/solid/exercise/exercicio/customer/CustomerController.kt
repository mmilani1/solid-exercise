package br.com.solid.exercise.exercicio.customer

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping("/customers")
    fun create(@Valid @RequestBody customerRequest: CustomerRequest): ResponseEntity<Void> {
        val customer = customerRequest.toCustomer()

        customerService.create(customer)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}