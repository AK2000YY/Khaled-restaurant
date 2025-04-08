package com.example.khaled_restaurant.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.repository.CustomerRepository
import com.example.khaled_restaurant.domain.repository.StreetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.apache.commons.text.similarity.LevenshteinDistance
import javax.inject.Inject
import kotlin.math.min

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val streetRepository: StreetRepository
) : ViewModel(){

    private val levenshtein = LevenshteinDistance()
    private val _filter = MutableStateFlow<FilterType>(FilterType.NameType(""))
    private val _customers = _filter.flatMapLatest { filter ->
        filter(filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(UiState())
    val state = combine(_filter, _customers, _state) { filter, customers, state ->
        state.copy(
            customers = customers,
            searchValue = filter
        )
    }

    fun onEvent(event: CustomerEvent) {
        when(event) {
            is CustomerEvent.AddOrUpdateCustomer -> {
                viewModelScope.launch {
                    customerRepository.updateOrAddCustomer(event.customer)
                }
            }
            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    customerRepository.deleteCustomer(event.customer)
                }
            }
            is CustomerEvent.FilterCustomer -> {
                _filter.value = event.type
            }
            is CustomerEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }
            is CustomerEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        showDialog = true
                    )
                }
            }
        }
    }

    private suspend fun filter(filter: FilterType): Flow<List<Customer>> {
        return when(filter) {
            is FilterType.NameType ->
                customerRepository.getAllCustomer().map { customers ->
                    customers.map { customer ->
                        val similarity = levenshtein.apply(filter.name, customer.name!!.substring(0, min(filter.name.length, customer.name.length)).lowercase())
                        Pair(customer, similarity)
                    }.sortedBy {
                        it.second
                    }.map {
                        it.first
                    }
                }
            is FilterType.PhoneType ->
                customerRepository.getAllCustomer().map { customers ->
                    customers.map { customer ->
                        val similarity = levenshtein.apply(filter.phone, customer.phone!!.substring(0, min(filter.phone.length, customer.phone.length)).lowercase())
                        Pair(customer, similarity)
                    }.sortedBy {
                        it.second
                    }.map {
                        it.first
                    }
                }
            is FilterType.StreetNameType -> {
                val streetsId = streetRepository.getStreet().map { streets ->
                    streets.map { street ->
                        val similarity = levenshtein.apply(filter.streetName, street.name!!.substring(0, min(filter.streetName.length, street.name.length)).lowercase())
                        Pair(street, similarity)
                    }.sortedBy {
                        it.second
                    }.map {
                        it.first.id
                    }
                }
                return streetsId.flatMapLatest {
                    customerRepository.getAllCustomer().map { customers ->
                        customers.sortedBy { customer ->
                            it.indexOf(customer.streetId)
                        }
                    }
                }
            }
        }
    }
}