package com.example.khaled_restaurant.presentation.street.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.presentation.street.StreetEvent

@Composable
fun AddStreetDialog(
    modifier: Modifier = Modifier,
    onEvent: (StreetEvent) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(StreetEvent.ShowDialog(false)) },
        title = {
            Text(text = "Add Street")
        },
        text = {
            Column {
                Text(text = "Add new street")
                CustomTextField(
                    color = Color.Black,
                    unFocusColor = Color.LightGray,
                    focusColor = Color.Black,
                    placeholder = "street name",
                    input = name,
                    changeInput = {
                        name = it
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onEvent(StreetEvent.AddStreet(Street(null, name))) }) {
                Text(
                    text = "Add Street",
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(StreetEvent.ShowDialog(false)) }) {
                Text(
                    text = "cancel",
                    color = Color.Black
                )
            }
        }
    )
}