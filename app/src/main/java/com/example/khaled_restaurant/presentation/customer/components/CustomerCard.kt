package com.example.khaled_restaurant.presentation.customer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.khaled_restaurant.domain.model.Customer
import com.example.khaled_restaurant.presentation.customer.CustomerEvent
import com.example.khaled_restaurant.presentation.customer.DialogType

@Composable
fun CustomerCard(
    modifier: Modifier = Modifier,
    customer: Customer,
    streetName: String,
    onEvent: (CustomerEvent) -> Unit
) {
    var hidden by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = modifier
            .padding(8.dp)
            .height(if(hidden) 60.dp else 140.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = customer.name.toString())
            Row {
                CustomIcon(
                    imageVector = Icons.Default.Edit,
                    color = Color.DarkGray,
                    onClick = {
                        onEvent(CustomerEvent.ShowDialog(DialogType.Update))
                    }
                )
                CustomIcon(
                    imageVector = Icons.Default.Delete,
                    color = Color.Red,
                    onClick = {
                        onEvent(CustomerEvent.ShowDialog(DialogType.Delete))
                    }
                )
                CustomIcon(
                    imageVector = if (hidden) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    color = Color.DarkGray,
                    onClick = {
                        hidden = !hidden
                    }
                )
            }
        }
        if (!hidden) {
            CustomRow(type = "Phone", value = customer.phone.toString())
            CustomRow(type = "Street", value = streetName)
        }
    }
}

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    type: String,
    value: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = type)
        Text(text = value)
    }
}

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .clickable { onClick() },
        imageVector = imageVector,
        contentDescription = null,
        tint = color
    )
}