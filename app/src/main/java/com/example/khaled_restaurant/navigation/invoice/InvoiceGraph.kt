package com.example.khaled_restaurant.navigation.invoice

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.StreetsGraph

fun NavGraphBuilder.invoiceGraph(
    navHostController: NavHostController
) {
    navigation<StreetsGraph>(
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