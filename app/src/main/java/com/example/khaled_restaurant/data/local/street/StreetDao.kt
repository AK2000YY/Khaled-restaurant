package com.example.khaled_restaurant.data.local.street

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface StreetDao {

    @Upsert
    suspend fun saveOrUpdateStreet()

    @Delete
    suspend fun deleteStreet()

    @Query("select * from StreetEntity")
    suspend fun getAllStreet(): Flow<List<StreetEntity>>

}