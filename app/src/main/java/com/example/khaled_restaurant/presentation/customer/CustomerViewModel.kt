package com.example.khaled_restaurant.presentation.customer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.khaled_restaurant.domain.repository.CustomerRepository
import com.example.khaled_restaurant.navigation.street.CustomersView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val customerRepository: CustomerRepository
) : ViewModel(){
    val streetId = MutableStateFlow(handle.toRoute<CustomersView>().streetId)

    init {
        println("ak2000yy + ${streetId.value}")
    }
}