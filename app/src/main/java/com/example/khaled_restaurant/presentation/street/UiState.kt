package com.example.khaled_restaurant.presentation.street

import com.example.khaled_restaurant.domain.model.Street

data class UiState(
    val streets: List<Street> = emptyList(),
    val searchName: String = "",
    val showDialog: Boolean = false,
    val dialogType: DialogType = DialogType.DELETE
)
