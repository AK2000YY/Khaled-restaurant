package com.example.khaled_restaurant.domain.repository

import com.example.khaled_restaurant.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun getAllCustomer(): Flow<List<Customer>>

    suspend fun updateOrAddCustomer(customer: Customer)

    suspend fun deleteCustomer(customer: Customer)

    suspend fun filterCustomerByStreet(streetName: String): Flow<List<Customer>>

    suspend fun filterCustomerByName(name: String): Flow<List<Customer>>

}