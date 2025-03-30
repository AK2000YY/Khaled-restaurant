package com.example.khaled_restaurant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.khaled_restaurant.navigation.app.appGraph
import com.example.khaled_restaurant.navigation.customer.customerGraph
import com.example.khaled_restaurant.navigation.food.foodGraph
import com.example.khaled_restaurant.navigation.invoice.invoiceGraph
import com.example.khaled_restaurant.navigation.street.streetGraph

@Composable
fun NavSetup(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = AppGraph
    ) {
        appGraph(
            navHostController = navHostController
        )

        streetGraph(
            navHostController = navHostController
        )

        customerGraph(
            navHostController = navHostController
        )

        foodGraph(
            navHostController = navHostController
        )

        invoiceGraph(
            navHostController = navHostController
        )
    }
}