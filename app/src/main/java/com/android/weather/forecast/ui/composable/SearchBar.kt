package com.android.weather.forecast.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import com.android.weather.forecast.ui.forecast.WeatherForecastViewModel

@ExperimentalMaterial3Api
@Composable
fun SearchView(viewModel: WeatherForecastViewModel) {
    var searchKey by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = searchKey,
            colors = textFieldColors(
                cursorColor = Color.Black,
                disabledLabelColor = Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                searchKey = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        Button(onClick = {
            if (searchKey.length > 3) {
                viewModel.searchWeatherForecast(searchKey)
            }
        }) {
            Text("Get Weather")
        }
    }
}