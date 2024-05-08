package com.android.weather.forecast.ui.forecast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.android.weather.forecast.data.model.Status
import com.android.weather.forecast.domain.model.WeatherItem
import com.android.weather.forecast.ui.composable.ErrorScreen
import com.android.weather.forecast.ui.composable.ProgressScreen
import timber.log.Timber

@Composable
fun WeatherContent(
    viewModel: WeatherForecastViewModel,
    successScreen: @Composable (list: List<WeatherItem>) -> Unit
) {
    val resourceState = viewModel.stateFlow.collectAsState().value
    when (resourceState.status) {
        Status.LOADING -> ProgressScreen()
        Status.SUCCESS -> {
            val isLoading = viewModel.isLoading.collectAsState().value
            if (!isLoading) {
                resourceState.data?.let {
                    Timber.tag("WeatherContent").i("length ==> ${it.size}")
                    successScreen(it)
                }
            }
        }
        Status.ERROR ->
            resourceState.message?.let { ErrorScreen(message = it, refresh = viewModel::refresh) }
    }
}