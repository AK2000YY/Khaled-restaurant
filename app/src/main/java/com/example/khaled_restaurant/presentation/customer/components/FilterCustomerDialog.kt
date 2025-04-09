package com.example.khaled_restaurant.presentation.customer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.khaled_restaurant.presentation.customer.CustomerEvent
import com.example.khaled_restaurant.presentation.customer.FilterType

@Composable
fun FilterCustomerDialog(
    modifier: Modifier = Modifier,
    onEvent: (CustomerEvent) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onEvent(CustomerEvent.HideDialog)
        },
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                CustomChoice(
                    filterName = "NAME",
                    onEvent = {
                        onEvent(CustomerEvent.FilterCustomerType(FilterType.NameType))
                    }
                )
                CustomChoice(
                    filterName = "PHONE",
                    onEvent = {
                        onEvent(CustomerEvent.FilterCustomerType(FilterType.PhoneType))
                    }
                )
                CustomChoice(
                    filterName = "STREET NAME",
                    onEvent = {
                        onEvent(CustomerEvent.FilterCustomerType(FilterType.StreetNameType))
                    }
                )
            }
        }
    }
}

@Composable
fun CustomChoice(
    modifier: Modifier = Modifier,
    filterName: String,
    onEvent: () -> Unit
) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                selected = !selected
                onEvent()
            }
        )
        Text(text = filterName)
    }
}