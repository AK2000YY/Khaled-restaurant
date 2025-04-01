package com.example.khaled_restaurant.presentation.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khaled_restaurant.domain.model.Response

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    toMainScreen: () -> Unit,
    viewModel: LandingViewModel = hiltViewModel()
) {

    Box(
        modifier = modifier
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "KHALED RESTAURANT",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }

    if(viewModel.response == Response.Success(true))
        LaunchedEffect(Unit) {
            toMainScreen()
        }
}