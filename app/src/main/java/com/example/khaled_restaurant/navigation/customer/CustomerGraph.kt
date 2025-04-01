package com.example.khaled_restaurant.navigation.customer

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.CustomersGraph

fun NavGraphBuilder.customerGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<CustomersGraph>(
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