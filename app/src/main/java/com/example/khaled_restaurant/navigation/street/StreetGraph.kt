package com.example.khaled_restaurant.navigation.street

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.StreetsGraph
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
                modifier = modifier
            )
        }

        composable<StreetAdd> {

        }

        composable<StreetUpdate> {

        }
    }
}