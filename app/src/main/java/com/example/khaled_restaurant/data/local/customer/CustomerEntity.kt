package com.example.khaled_restaurant.data.local.customer

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.khaled_restaurant.domain.model.Customer

@Entity(
    indices = [
        Index(value = ["phone"], unique = true)
    ]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val phone: String?,
    val streetId: Int?
)

fun CustomerEntity.toModel() =
    Customer(
        id,
        name,
        phone,
        streetId
    )