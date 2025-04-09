package com.example.khaled_restaurant.presentation.customer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.khaled_restaurant.presentation.customer.CustomerEvent
import com.example.khaled_restaurant.presentation.customer.DialogType
import com.example.khaled_restaurant.presentation.street.components.CustomTextField

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    onEvent: (CustomerEvent) -> Unit
) {
    var search by rememberSaveable {
        mutableStateOf("")
    }
    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CustomTextField(
            modifier = Modifier
                .weight(8f),
            unFocusColor = Color.LightGray,
            focusColor = Color.White,
            input = search,
            changeInput = {
                search = it
                onEvent(CustomerEvent.FilterCustomerValue(search))
            }
        )
        Icon(
            modifier = Modifier
                .weight(2f)
                .height(35.dp)
                .clickable {
                    onEvent(CustomerEvent.ShowDialog(DialogType.Filter))
                },
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = null,
            tint = Color.White
        )
    }
}