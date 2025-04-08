package com.example.khaled_restaurant.data.repository

import com.example.khaled_restaurant.data.local.customer.CustomerDao
import com.example.khaled_restaurant.data.local.customer.toModel
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.model.toEntity
import com.example.khaled_restaurant.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImp @Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository {

    override suspend fun getAllCustomer(): Flow<List<Customer>> =
        customerDao.getAllCustomer().map { customerEntities ->
            customerEntities.map { customerEntity ->
                customerEntity.toModel()
            }
        }

    override suspend fun updateOrAddCustomer(customer: Customer) =
        customerDao.saveOrUpdateCustomer(customer.toEntity())

    override suspend fun deleteCustomer(customer: Customer) =
        customerDao.deleteCustomer(customer.toEntity())


}