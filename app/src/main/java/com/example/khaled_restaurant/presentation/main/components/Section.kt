package com.example.khaled_restaurant.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.Section(
    modifier: Modifier = Modifier,
    sectionName: String,
    icon: ImageVector,
    toSection: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(5.dp)
            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable {
                toSection()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = sectionName,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 25.sp
        )
    }
}