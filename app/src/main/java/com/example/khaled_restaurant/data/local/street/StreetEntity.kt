package com.example.khaled_restaurant.data.local.street

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.khaled_restaurant.domain.model.Street


@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class StreetEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String?
)

fun StreetEntity.toModel() = Street(
    id,
    name
)