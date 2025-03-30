package com.example.khaled_restaurant.navigation.street

import kotlinx.serialization.Serializable

@Serializable
data object StreetsView

@Serializable
data object StreetAdd

@Serializable
data class StreetUpdate(
    val id: Int
)