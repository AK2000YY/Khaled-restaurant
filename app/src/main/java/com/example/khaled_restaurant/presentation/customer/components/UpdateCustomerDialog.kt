package com.example.khaled_restaurant.presentation.customer.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.presentation.customer.CustomerEvent
import com.example.khaled_restaurant.presentation.street.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun UpdateCustomerDialog(
    modifier: Modifier = Modifier,
    streetName: String,
    customer: Customer,
    streets: List<Street>,
    onEvent: (CustomerEvent) -> Unit
) {
    var streetSearch by rememberSaveable { mutableStateOf(streetName) }
    var name by rememberSaveable { mutableStateOf(customer.name.toString()) }
    var phone by rememberSaveable { mutableStateOf(customer.phone.toString()) }
    var expand by rememberSaveable { mutableStateOf(false) }
    var streetId by rememberSaveable {
        mutableIntStateOf(-1)
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(CustomerEvent.HideDialog) },
        title = { Text(text = "Edit Customer") },
        text = {
            Column {
                CustomTextField(
                    color = Color.Black,
                    unFocusColor = Color.LightGray,
                    focusColor = Color.Black,
                    input = name,
                    changeInput = { name = it }
                )
                CustomTextField(
                    color = Color.Black,
                    unFocusColor = Color.LightGray,
                    focusColor = Color.Black,
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
                onEvent(CustomerEvent.AddOrUpdateCustomer(Customer(customer.id, name, phone, streetId)))
            }) {
                Text(text = "Update Customer", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(CustomerEvent.HideDialog) }) {
                Text(text = "Cancel", color = Color.Black)
            }
        }
    )
}