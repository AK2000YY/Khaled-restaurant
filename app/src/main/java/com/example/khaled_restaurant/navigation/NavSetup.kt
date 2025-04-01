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
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = AppGraph
    ) {
        appGraph(
            modifier = modifier,
            navHostController = navHostController
        )

        streetGraph(
            modifier = modifier,
            navHostController = navHostController
        )

        customerGraph(
            modifier = modifier,
            navHostController = navHostController
        )

        foodGraph(
            modifier = modifier,
            navHostController = navHostController
        )

        invoiceGraph(
            modifier = modifier,
            navHostController = navHostController
        )
    }
}