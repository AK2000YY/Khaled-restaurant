package com.example.khaled_restaurant.domain.repository

import com.example.khaled_restaurant.domain.model.Response
import com.example.khaled_restaurant.domain.model.Street
import kotlinx.coroutines.flow.Flow


interface StreetRepository {
    suspend fun getStreet(): Flow<List<Street>>
    suspend fun getStreetBySearch(name: String): Flow<List<Street>>
    suspend fun insertOrUpdateStreet(street: Street)
    suspend fun deleteStreet(street: Street)
}