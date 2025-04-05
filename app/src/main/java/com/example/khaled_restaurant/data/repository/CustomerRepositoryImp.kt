package com.example.khaled_restaurant.data.repository

import com.example.khaled_restaurant.data.local.customer.CustomerDao
import com.example.khaled_restaurant.data.local.customer.toModel
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.model.toEntity
import com.example.khaled_restaurant.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.apache.commons.text.similarity.LevenshteinDistance
import javax.inject.Inject
import kotlin.math.min

class CustomerRepositoryImp @Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository{

    private val levenshtein = LevenshteinDistance()

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

    override suspend fun getCustomerByStreetId(streetId: Int): Flow<List<Customer>> =
        customerDao.getAllCustomerByStreetId(streetId).map { customerEntities ->
            customerEntities.map { customerEntity ->
                customerEntity.toModel()
            }
        }

    override suspend fun filterCustomerByName(name: String): Flow<List<Customer>> =
        customerDao.getAllCustomer().map { customerEntities ->
            customerEntities
                .map { customerEntity ->
                    val similarity = levenshtein.apply(name.lowercase(), customerEntity.name!!.substring(0, min(name.length, customerEntity.name.length)).lowercase())
                    Pair(customerEntity, similarity)
                }
                .sortedBy { it.second }
                .map {
                    it.first.toModel()
                }
        }
}