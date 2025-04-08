package com.example.khaled_restaurant.presentation.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khaled_restaurant.presentation.customer.components.AddCustomerDialog
import com.example.khaled_restaurant.presentation.customer.components.CustomerCard
import com.example.khaled_restaurant.presentation.customer.components.DeleteCustomerDialog
import com.example.khaled_restaurant.presentation.customer.components.FilterCustomerDialog
import com.example.khaled_restaurant.presentation.customer.components.UpdateCustomerDialog

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if(state.showDialog) {
        when(state.dialogType) {
            DialogType.Delete ->
                DeleteCustomerDialog()
            DialogType.Add ->
                AddCustomerDialog(
                    streets = state.selectedStreets,
                    onEvent = { event ->
                        viewModel.onEvent(event)
                    }
                )
            DialogType.Update ->
                UpdateCustomerDialog()
            DialogType.Filter ->
                FilterCustomerDialog()
        }
    }

    Box(
        modifier = modifier
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn {
                items(state.customers, key = {it.id!!}) {
                    CustomerCard(
                        customer = it,
                        streetName = state.streets[it.streetId] ?: "UNKNOWN",
                        onEvent = { event ->
                            viewModel.onEvent(event)
                        }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = {
                viewModel.onEvent(CustomerEvent.ShowDialog(DialogType.Add))
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            containerColor = Color.LightGray
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}