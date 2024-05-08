package com.android.weather.forecast.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.android.weather.forecast.ui.theme.WeatherForecastTheme

@Composable
fun DetailTitleText(resourceId: Int) {
    Text(
        text = stringResource(id = resourceId),
        fontWeight = FontWeight.Bold,
        fontSize = WeatherForecastTheme.fontSizes.sp_18
    )
}

@Composable
fun DetailDescriptionText(text: String) {
    Text(
        text = text,
        fontSize = WeatherForecastTheme.fontSizes.sp_16
    )
}