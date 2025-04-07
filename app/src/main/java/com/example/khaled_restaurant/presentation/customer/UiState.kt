package com.example.khaled_restaurant.presentation.customer

import com.example.khaled_restaurant.domain.model.Customer

data class UiState(
    val customers: List<Customer> = emptyList(),
    val showDialog: Boolean = false,
    val searchValue: FilterType = FilterType.NameType(""),
    val dialogType: DialogType = DialogType.Delete
)
