package com.example.khaled_restaurant.navigation.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.khaled_restaurant.navigation.AppGraph
import com.example.khaled_restaurant.presentation.landing.LandingScreen

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome Bro", color = Color.Black)
            }
        }
    }
}