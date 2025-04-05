package com.example.khaled_restaurant.presentation.street

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaled_restaurant.domain.repository.StreetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class StreetViewModel @Inject constructor(
    private val streetRepository: StreetRepository
) : ViewModel() {

    private val _searchName = MutableStateFlow("")
    private val _streets = _searchName
        .flatMapLatest { name ->
            streetRepository.getStreetBySearch(name.trim())
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(UiState())
    val state = combine(_state, _streets, _searchName) { state, streets, searchName ->
        state.copy(
            streets = streets,
            searchName = searchName
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState())

    fun onEvent(event: StreetEvent) {
        when (event) {
            is StreetEvent.DeleteStreet -> {
                viewModelScope.launch {
                    streetRepository.deleteStreet(event.street)
                    showOrHideDialog(false)
                }
            }

            is StreetEvent.SearchName -> {
                _state.update {
                    it.copy(searchName = event.name)
                }
                _searchName.update {
                    event.name
                }
            }

            is StreetEvent.ShowDialog -> {
                showOrHideDialog(event.show, event.type)
            }

            is StreetEvent.UpdateStreet -> {
                if(event.street.name?.trim()?.isEmpty() == true) return
                viewModelScope.launch {
                    streetRepository.insertOrUpdateStreet(event.street)
                    showOrHideDialog(false)
                }
            }

            is StreetEvent.AddStreet -> {
                if (event.street.name?.trim()?.isEmpty() == true) return
                viewModelScope.launch {
                    streetRepository.insertOrUpdateStreet(event.street)
                    showOrHideDialog(false)
                }
            }

            is StreetEvent.ToCustomers -> {
                _state.update {
                    it.copy(
                        toCustomers = event.streetId
                    )
                }
            }
        }

    }

    private fun showOrHideDialog(showDialog: Boolean, dialogType: DialogType = DialogType.DELETE) {
        _state.update {
            it.copy(
                showDialog = showDialog,
                dialogType = dialogType
            )
        }
    }

}