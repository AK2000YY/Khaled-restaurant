package com.example.khaled_restaurant.domain.model

import com.example.khaled_restaurant.data.local.street.StreetEntity

data class Street(
    val id: Int?,
    val name: String?
)

fun Street.toEntity() =
    StreetEntity(
        id,
        name
    )