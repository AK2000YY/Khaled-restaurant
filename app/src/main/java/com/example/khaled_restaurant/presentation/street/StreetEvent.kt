package com.example.khaled_restaurant.presentation.street

import com.example.khaled_restaurant.domain.model.Street

sealed class StreetEvent {
    data class SearchName(val name: String) : StreetEvent()
    data class DeleteStreet(val street: Street) : StreetEvent()
    data class ShowDialog(val show: Boolean) : StreetEvent()
    data object ToUpdatePage : StreetEvent()
    data object ToAddPage : StreetEvent()
}