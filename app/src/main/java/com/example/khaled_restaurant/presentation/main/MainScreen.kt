package com.example.khaled_restaurant.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.khaled_restaurant.presentation.main.components.Section

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    toStreetSection: () -> Unit,
    toCustomerSection: () -> Unit,
    toFoodSection: () -> Unit,
    toInvoiceSection: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Section(
                sectionName = "STREETS",
                icon = Icons.Default.Email,
                toSection = toStreetSection
            )
            Section(
                sectionName = "CUSTOMERS",
                icon = Icons.Default.Person,
                toSection = toCustomerSection
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Section(
                sectionName = "FOODS",
                icon = Icons.Default.Info,
                toSection = toFoodSection
            )
            Section(
                sectionName = "INVOICES",
                icon = Icons.Default.ShoppingCart,
                toSection = toInvoiceSection
            )
        }
    }
}