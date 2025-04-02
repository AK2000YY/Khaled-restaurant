package com.example.khaled_restaurant.data.local.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Upsert
    suspend fun saveOrUpdateCustomer()

    @Delete
    suspend fun deleteCustomer()

    @Query("select * from CustomerEntity")
    suspend fun getAllCustomer(): Flow<List<CustomerEntity>>
}