package com.example.khaled_restaurant.presentation.customer.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.presentation.customer.CustomerEvent
import com.example.khaled_restaurant.presentation.street.components.CustomTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import com.example.khaled_restaurant.domain.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AddCustomerDialog(
    modifier: Modifier = Modifier,
    streets: List<Street>,
    onEvent: (CustomerEvent) -> Unit
) {
    var streetSearch by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var expand by rememberSaveable { mutableStateOf(false) }
    var streetId by rememberSaveable {
        mutableIntStateOf(-1)
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(CustomerEvent.HideDialog) },
        title = { Text(text = "Add Customer") },
        text = {
            Column {
                Text(text = "Add new customer")
                CustomTextField(
                    color = Color.Black,
                    unFocusColor = Color.LightGray,
                    focusColor = Color.Black,
                    placeholder = "customer name",
                    input = name,
                    changeInput = { name = it }
                )
                CustomTextField(
                    color = Color.Black,
                    unFocusColor = Color.LightGray,
                    focusColor = Color.Black,
                    placeholder = "customer phone",
                    input = phone,
                    changeInput = { phone = it }
                )
                ExposedDropdownMenuBox(
                    expanded = expand,
                    onExpandedChange = { expand = !expand },
                ) {
                    CustomTextField(
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryEditable, true),
                        color = Color.Black,
                        unFocusColor = Color.LightGray,
                        focusColor = Color.Black,
                        placeholder = "customer street",
                        input = streetSearch,
                        changeInput = {
                            streetSearch = it
                            onEvent(CustomerEvent.ChangeSelectedStreetName(streetSearch))
                            expand = it.isNotEmpty()
                        }
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier
                            .heightIn(max = 90.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        expanded = expand,
                        onDismissRequest = { expand = false },
                    ) {
                        streets.forEach { street ->
                            DropdownMenuItem(
                                text = { Text(street.name ?: "") },
                                onClick = {
                                    streetId = street.id!!
                                    streetSearch = street.name!!
                                    expand = false
                                }
                            )
                        }
                    }
                }
            }

        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(CustomerEvent.AddOrUpdateCustomer(Customer(null, name, phone, streetId)))
            }) {
                Text(text = "Add Customer", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(CustomerEvent.HideDialog) }) {
                Text(text = "Cancel", color = Color.Black)
            }
        }
    )
}
