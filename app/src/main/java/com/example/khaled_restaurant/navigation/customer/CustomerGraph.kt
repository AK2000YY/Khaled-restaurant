package com.example.khaled_restaurant.navigation.customer

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.CustomersGraph
import com.example.khaled_restaurant.navigation.StreetsGraph
import com.example.khaled_restaurant.presentation.customer.CustomerScreen

fun NavGraphBuilder.customerGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<CustomersGraph>(
        startDestination = CustomersView
    ) {
        composable<CustomersView> {
            CustomerScreen(
                modifier = modifier
            )
        }
    }
}