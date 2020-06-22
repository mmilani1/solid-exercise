package br.com.solid.exercise.exercicio.customer

import org.springframework.data.repository.RepositoryDefinition
import java.util.*

@RepositoryDefinition(domainClass = CustomerEntity::class, idClass = UUID::class)
interface CustomerRepository {
    fun save(customer: CustomerEntity)
}