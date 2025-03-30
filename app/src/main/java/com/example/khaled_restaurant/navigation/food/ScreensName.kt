package com.example.khaled_restaurant.navigation.food

import kotlinx.serialization.Serializable

@Serializable
data object FoodsView

@Serializable
data object FoodAdd

@Serializable
data class FoodUpdate(
    val id: Int
)