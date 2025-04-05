package com.example.khaled_restaurant.navigation.street

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.khaled_restaurant.navigation.StreetsGraph
import com.example.khaled_restaurant.presentation.customer.CustomerScreen
import com.example.khaled_restaurant.presentation.street.StreetScreen

fun NavGraphBuilder.streetGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<StreetsGraph>(
        startDestination = StreetsView
    ) {
        composable<StreetsView> {
            StreetScreen(
                modifier = modifier,
                toCustomers = { streetId ->
                    navHostController.navigate(CustomersView(streetId = streetId))
                }
            )
        }
        composable<CustomersView> {
                CustomerScreen(
                    modifier = modifier
                )
        }
    }
}