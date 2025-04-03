package com.example.khaled_restaurant.domain.model

import com.example.khaled_restaurant.data.local.customer.CustomerEntity

data class Customer(
    val id: Int?,
    val name: String?,
    val phone: String?,
    val streetId: Int?
)

fun Customer.toEntity() =
    CustomerEntity(
        id,
        name,
        phone,
        streetId
    )