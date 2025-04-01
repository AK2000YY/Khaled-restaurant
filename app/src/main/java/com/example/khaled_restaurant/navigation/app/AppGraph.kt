package com.example.khaled_restaurant.navigation.app

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.AppGraph
import com.example.khaled_restaurant.navigation.CustomersGraph
import com.example.khaled_restaurant.navigation.FoodsGraph
import com.example.khaled_restaurant.navigation.InvoicesGraph
import com.example.khaled_restaurant.navigation.StreetsGraph
import com.example.khaled_restaurant.presentation.landing.LandingScreen
import com.example.khaled_restaurant.presentation.main.MainScreen

fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<AppGraph>(
        startDestination = LandingScreen
    ) {
        composable<LandingScreen> {
            LandingScreen(
                modifier = modifier,
                toMainScreen = {
                    navHostController.navigate(MainScreen) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<MainScreen> {
            MainScreen(
                modifier = modifier,
                toStreetSection = {
                    navHostController.navigate(StreetsGraph)
                },
                toCustomerSection = {
                    navHostController.navigate(CustomersGraph)
                },
                toFoodSection = {
                    navHostController.navigate(FoodsGraph)
                },
                toInvoiceSection = {
                    navHostController.navigate(InvoicesGraph)
                }
            )
        }
    }
}