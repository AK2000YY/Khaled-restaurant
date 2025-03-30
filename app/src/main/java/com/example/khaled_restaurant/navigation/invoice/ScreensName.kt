package com.example.khaled_restaurant.navigation.invoice

import kotlinx.serialization.Serializable

@Serializable
data object InvoicesView

@Serializable
data object InvoiceAdd

@Serializable
data class InvoiceUpdate(
    val id: Int
)