package com.android.weather.forecast.ui.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.weather.forecast.ui.composable.AppComposable
import com.android.weather.forecast.ui.composable.SearchView

@ExperimentalMaterial3Api
@Composable
fun WeatherForecastScreen() {
    val viewModel = hiltViewModel<WeatherForecastViewModel>()

    AppComposable(
        title= "Weather Forecast",
        content = {
            Column {
                SearchView(viewModel = viewModel)
                WeatherContent(viewModel = viewModel) { list ->
                    VerticalCollection(list) {}
                }
            }
        }
    )
}