package com.example.khaled_restaurant.presentation.street

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaled_restaurant.domain.model.Street
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
class StreetViewModel @Inject constructor(
    private val streetRepository: StreetRepository
) : ViewModel() {

    private val levenshtein = LevenshteinDistance()

    private val _searchName = MutableStateFlow("")
    private val _streets = _searchName
        .flatMapLatest { name ->
            filterByName(name)
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
        }

    }

    private suspend fun filterByName(name: String): Flow<List<Street>> =
        streetRepository.getStreet().map { streets ->
            streets
                .map { street ->
                    val similarity = levenshtein.apply(name.lowercase(), street.name!!.substring(0, min(name.length, street.name.length)).lowercase())
                    Pair(street, similarity)
                }
                .sortedBy { it.second }
                .map {
                    it.first
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