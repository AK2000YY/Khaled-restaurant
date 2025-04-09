package com.example.khaled_restaurant.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.model.Street
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
) : ViewModel() {

    private val levenshtein = LevenshteinDistance()
    private val _filterSelectedStreet = MutableStateFlow("")
    private val _selectedStreets = _filterSelectedStreet.flatMapLatest {
        filterSelectedStreet(it)
    }
    private val _filter = MutableStateFlow(Pair(FilterType.NameType, ""))
    private val _customers = _filter.flatMapLatest { filter ->
        filter(filter.first, filter.second)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(UiState())
    private val _streetsMap = MutableStateFlow<Map<Int, String>>(emptyMap())
    val state = combine(
        _filter,
        _customers,
        _state,
        _streetsMap,
        _selectedStreets
    ) { filter, customers, state, streetsMap, selectedStreets ->
        state.copy(
            customers = customers,
            searchType = filter.first,
            streets = streetsMap,
            selectedStreets = selectedStreets
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState())

    init {
        viewModelScope.launch {
            streetRepository.getStreet().collect {
                _streetsMap.value = it.associate { street -> street.id!! to street.name!! }
            }
        }
    }

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.AddOrUpdateCustomer -> {
                viewModelScope.launch {
                    if (event.customer.name!!.isEmpty() || event.customer.phone!!.isEmpty() || event.customer.streetId == -1)
                        return@launch
                    else {
                        customerRepository.updateOrAddCustomer(event.customer)
                        hideDialog()
                    }
                }
            }

            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    customerRepository.deleteCustomer(event.customer)
                    hideDialog()
                }
            }

            is CustomerEvent.FilterCustomerType -> {
                _filter.update {
                    it.copy(
                        first = event.type
                    )
                }
                hideDialog()
            }

            is CustomerEvent.FilterCustomerValue -> {
                _filter.update {
                    it.copy(
                        second = event.search
                    )
                }
            }

            is CustomerEvent.HideDialog -> {
                hideDialog()
            }

            is CustomerEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        showDialog = true,
                        dialogType = event.type
                    )
                }
            }

            is CustomerEvent.ChangeSelectedStreetName -> {
                _filterSelectedStreet.value = event.name
            }
        }
    }

    private fun hideDialog() {
        _state.update {
            it.copy(
                showDialog = false
            )
        }
    }

    private suspend fun filterSelectedStreet(name: String): Flow<List<Street>> =
        streetRepository.getStreet().map { streets ->
            streets.map { street ->
                val similarity = levenshtein.apply(
                    name.lowercase(),
                    street.name!!.substring(0, min(name.length, street.name.length)).lowercase()
                )
                Pair(street, similarity)
            }.sortedBy {
                it.second
            }.map {
                it.first
            }
        }

    private suspend fun filter(filter: FilterType, searchValue: String): Flow<List<Customer>> {
        return when (filter) {
            FilterType.NameType ->
                customerRepository.getAllCustomer().map { customers ->
                    customers.map { customer ->
                        val similarity = levenshtein.apply(
                            searchValue.lowercase(),
                            customer.name!!.substring(
                                0,
                                min(searchValue.length, customer.name.length)
                            ).lowercase()
                        )
                        Pair(customer, similarity)
                    }.sortedBy {
                        it.second
                    }.map {
                        it.first
                    }
                }

            FilterType.PhoneType ->
                customerRepository.getAllCustomer().map { customers ->
                    customers.map { customer ->
                        val similarity = levenshtein.apply(
                            searchValue.lowercase(),
                            customer.phone!!.substring(
                                0,
                                min(searchValue.length, customer.phone.length)
                            ).lowercase()
                        )
                        Pair(customer, similarity)
                    }.sortedBy {
                        it.second
                    }.map {
                        it.first
                    }
                }

            FilterType.StreetNameType -> {
                val streetsId = streetRepository.getStreet().map { streets ->
                    streets.map { street ->
                        val similarity = levenshtein.apply(
                            searchValue.lowercase(),
                            street.name!!.substring(
                                0,
                                min(searchValue.length, street.name.length)
                            ).lowercase()
                        )
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