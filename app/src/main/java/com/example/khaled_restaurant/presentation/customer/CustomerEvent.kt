package com.example.khaled_restaurant.presentation.customer

import com.example.khaled_restaurant.domain.model.Customer

sealed class CustomerEvent {
    data class FilterCustomer(val type: FilterType = FilterType.NameType("")) : CustomerEvent()
    data class AddOrUpdateCustomer(val customer: Customer) : CustomerEvent()
    data class DeleteCustomer(val customer: Customer) : CustomerEvent()
    data class ChangeSelectedStreetName(val name: String) : CustomerEvent()
    data class ShowDialog(val type: DialogType = DialogType.Delete) : CustomerEvent()
    data object HideDialog : CustomerEvent()
}