package com.example.khaled_restaurant.navigation.customer

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.StreetsGraph

fun NavGraphBuilder.customerGraph(
    navHostController: NavHostController
) {
    navigation<StreetsGraph>(
        startDestination = CustomersView
    ) {
        composable<CustomersView> {

        }

        composable<CustomerAdd> {

        }

        composable<CustomerUpdate> {

        }
    }
}