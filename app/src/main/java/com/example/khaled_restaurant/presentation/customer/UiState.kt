package com.example.khaled_restaurant.presentation.customer

import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.model.Street

data class UiState(
    val customers: List<Customer> = emptyList(),
    val streets: Map<Int, String> = emptyMap(),
    val selectedStreets: List<Street> = emptyList(),
    val showDialog: Boolean = false,
    val searchType: FilterType = FilterType.NameType,
    val searchValue: String = "",
    val dialogType: DialogType = DialogType.Delete
)
