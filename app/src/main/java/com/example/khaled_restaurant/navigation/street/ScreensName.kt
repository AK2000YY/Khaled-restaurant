package com.example.khaled_restaurant.navigation.street

import kotlinx.serialization.Serializable

@Serializable
data object StreetsView

@Serializable
data class CustomersView(
    val streetId: Int? = null
)