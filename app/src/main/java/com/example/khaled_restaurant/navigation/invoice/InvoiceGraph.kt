package com.example.khaled_restaurant.navigation.invoice

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.InvoicesGraph

fun NavGraphBuilder.invoiceGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<InvoicesGraph>(
        startDestination = InvoicesView
    ) {
        composable<InvoicesView> {

        }

        composable<InvoiceAdd> {

        }

        composable<InvoiceUpdate> {

        }
    }
}