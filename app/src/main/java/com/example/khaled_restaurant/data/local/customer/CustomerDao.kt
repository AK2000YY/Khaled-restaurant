package com.example.khaled_restaurant.data.local.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Upsert
    suspend fun saveOrUpdateCustomer(customerEntity: CustomerEntity)

    @Delete
    suspend fun deleteCustomer(customerEntity: CustomerEntity)

    @Query("select * from CustomerEntity")
    fun getAllCustomer(): Flow<List<CustomerEntity>>

    @Query("select * from CustomerEntity where streetId == :streetId")
    fun getAllCustomerByStreetId(streetId: Int): Flow<List<CustomerEntity>>
}