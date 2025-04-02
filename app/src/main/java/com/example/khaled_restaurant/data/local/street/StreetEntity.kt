package com.example.khaled_restaurant.data.local.street

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StreetEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?
)