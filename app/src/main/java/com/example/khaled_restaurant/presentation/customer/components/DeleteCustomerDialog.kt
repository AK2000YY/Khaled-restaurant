package com.example.khaled_restaurant.presentation.customer.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.presentation.customer.CustomerEvent

@Composable
fun DeleteCustomerDialog(
    modifier: Modifier = Modifier,
    customer: Customer,
    onEvent: (CustomerEvent) -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(CustomerEvent.HideDialog) },
        title = {
            Text(text = "Confirm Deletion")
        },
        text = {
            Text(text = "Are you sure to delete this customer?")
        },
        confirmButton = {
            TextButton(onClick = { onEvent(CustomerEvent.DeleteCustomer(customer)) }) {
                Text(
                    text = "delete",
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(CustomerEvent.HideDialog) }) {
                Text(
                    text = "cancel",
                    color = Color.Black
                )
            }
        }
    )
}