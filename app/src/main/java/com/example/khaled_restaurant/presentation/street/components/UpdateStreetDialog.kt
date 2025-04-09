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
fun UpdateStreetDialog(
    modifier: Modifier = Modifier,
    onEvent: (StreetEvent) -> Unit,
    street: Street
) {
    var name by remember {
        mutableStateOf(street.name.toString())
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(StreetEvent.ShowDialog(false)) },
        title = {
            Text(text = "Edit Street")
        },
        text = {
            Column {
                Text(text = "Update this street")
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
            TextButton(onClick = { onEvent(StreetEvent.AddStreet(street.copy(name = name))) }) {
                Text(
                    text = "Update Street",
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