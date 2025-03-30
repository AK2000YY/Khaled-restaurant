package com.example.khaled_restaurant.navigation.customer

import kotlinx.serialization.Serializable

@Serializable
data object CustomersView

@Serializable
data object CustomerAdd

@Serializable
data class CustomerUpdate(
    val id: Int
)