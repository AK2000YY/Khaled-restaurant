package com.example.khaled_restaurant.presentation.customer

sealed class FilterType {
    data class NameType(val name: String) : FilterType()
    data class PhoneType(val phone: String) : FilterType()
    data class StreetNameType(val streetName: String) : FilterType()
}