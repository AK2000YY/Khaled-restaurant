package com.example.khaled_restaurant.data.local.street

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface StreetDao {

    @Upsert
    suspend fun saveOrUpdateStreet(streetEntity: StreetEntity)

    @Delete
    suspend fun deleteStreet(streetEntity: StreetEntity)

    @Query("select * from StreetEntity")
    fun getAllStreet(): Flow<List<StreetEntity>>

}