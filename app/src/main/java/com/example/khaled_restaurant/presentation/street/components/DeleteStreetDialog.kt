package com.example.khaled_restaurant.presentation.street.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.presentation.street.StreetEvent

@Composable
fun DeleteStreetDialog(
    modifier: Modifier = Modifier,
    onEvent: (StreetEvent) -> Unit,
    street: Street
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(StreetEvent.ShowDialog(false)) },
        title = {
            Text(text = "Confirm Deletion")
        },
        text = {
            Text(text = "Are you sure to delete this street?")
        },
        confirmButton = {
            TextButton(onClick = { onEvent(StreetEvent.DeleteStreet(street)) }) {
                Text(
                    text = "delete",
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