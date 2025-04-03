package com.example.khaled_restaurant.presentation.street

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khaled_restaurant.presentation.street.components.StreetCard

@Composable
fun StreetScreen(
    modifier: Modifier = Modifier,
    viewModel: StreetViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Box(
        modifier = modifier
            .background(Color.Black)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            TextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "Search Streets", color = Color.Gray, fontSize = 12.sp)
                },
                textStyle = TextStyle(color = Color.White, fontSize = 12.sp),
                value = state.searchName,
                onValueChange = {
                    viewModel.onEvent(StreetEvent.SearchName(it))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.White
                ),
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                items(state.streets, key = { it.id!! }) {
                    StreetCard(
                        streetName = it.name.toString(),
                        delete = {},
                        edit = {}
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = {},
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