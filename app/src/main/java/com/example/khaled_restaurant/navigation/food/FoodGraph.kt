package com.example.khaled_restaurant.navigation.food

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.StreetsGraph

fun NavGraphBuilder.foodGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<StreetsGraph>(
        startDestination = FoodsView
    ) {
        composable<FoodsView> {

        }

        composable<FoodAdd> {

        }

        composable<FoodUpdate> {

        }
    }
}