package com.example.khaled_restaurant.presentation.customer

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val state by viewModel.streetId.collectAsState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.toString())
    }

}