package com.example.khaled_restaurant.presentation.street

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.presentation.street.components.AddStreetDialog
import com.example.khaled_restaurant.presentation.street.components.CustomTextField
import com.example.khaled_restaurant.presentation.street.components.DeleteStreetDialog
import com.example.khaled_restaurant.presentation.street.components.StreetCard
import com.example.khaled_restaurant.presentation.street.components.UpdateStreetDialog

@Composable
fun StreetScreen(
    modifier: Modifier = Modifier,
    viewModel: StreetViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    var street by remember {
        mutableStateOf(Street(null, null))
    }

    if (state.showDialog) {
        when (state.dialogType) {
            DialogType.ADD ->
                AddStreetDialog(
                    onEvent = {
                        viewModel.onEvent(it)
                    }
                )

            DialogType.DELETE ->
                DeleteStreetDialog(
                    onEvent = {
                        viewModel.onEvent(it)
                    },
                    street = street
                )

            DialogType.UPDATE ->
                UpdateStreetDialog(
                    onEvent = {
                        viewModel.onEvent(it)
                    },
                    street = street
                )
        }
    }

    Box(
        modifier = modifier
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                unFocusColor = Color.LightGray,
                focusColor = Color.White,
                placeholder = "Search street",
                input = state.searchName,
                changeInput = {
                    viewModel.onEvent(StreetEvent.SearchName(it))
                }
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                items(state.streets, key = { it.id!! }) {
                    StreetCard(
                        streetName = it.name.toString(),
                        delete = {
                            street = it
                            viewModel.onEvent(StreetEvent.ShowDialog(true, DialogType.DELETE))
                        },
                        edit = {
                            street = it
                            viewModel.onEvent(StreetEvent.ShowDialog(true, DialogType.UPDATE))
                        }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = {
                viewModel.onEvent(StreetEvent.ShowDialog(true, DialogType.ADD))
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