package com.example.khaled_restaurant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.khaled_restaurant.data.local.customer.CustomerDao
import com.example.khaled_restaurant.data.local.customer.CustomerEntity
import com.example.khaled_restaurant.data.local.street.StreetDao
import com.example.khaled_restaurant.data.local.street.StreetEntity

@Database(entities = [CustomerEntity::class, StreetEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val streetDao: StreetDao
    abstract val customerDao: CustomerDao
}