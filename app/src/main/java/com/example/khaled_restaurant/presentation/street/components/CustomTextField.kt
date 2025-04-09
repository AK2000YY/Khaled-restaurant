package com.example.khaled_restaurant.presentation.street.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    unFocusColor: Color,
    color: Color = Color.White,
    focusColor: Color,
    placeholder: String = "",
    input: String,
    changeInput: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(text = placeholder, color = Color.Gray, fontSize = 12.sp)
        },
        textStyle = TextStyle(color = color, fontSize = 12.sp),
        value = input,
        onValueChange = {
            changeInput(it)
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = unFocusColor,
            focusedIndicatorColor = focusColor,
            cursorColor = Color.Gray
        ),
    )
}