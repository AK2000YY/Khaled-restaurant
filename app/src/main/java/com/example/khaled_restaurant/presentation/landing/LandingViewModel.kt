package com.example.khaled_restaurant.presentation.landing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaled_restaurant.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
) : ViewModel() {
    var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    init {
        viewModelScope.launch {
            delay(3000)
            response = Response.Success(true)
        }
    }

}