package com.example.khaled_restaurant.presentation.street.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StreetCard(
    modifier: Modifier = Modifier,
    streetName: String,
    delete: () -> Unit,
    edit: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = streetName)
        Row {
            Icon(
                modifier = Modifier
                    .clickable {
                        edit()
                    },
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color.DarkGray
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        delete()
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}