package com.example.khaled_restaurant.navigation.street

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
import com.example.khaled_restaurant.navigation.StreetsGraph

fun NavGraphBuilder.streetGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation<StreetsGraph>(
        startDestination = StreetsView
    ) {
        composable<StreetsView> {
            Box(modifier = modifier
                .fillMaxSize()
                .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "from Streets", color = Color.White)
            }
        }

        composable<StreetAdd> {

        }

        composable<StreetUpdate> {

        }
    }
}