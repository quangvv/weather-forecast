package com.android.weather.forecast.ui.forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.weather.forecast.domain.model.WeatherItem
import timber.log.Timber

@Composable
fun VerticalCollection(
    weatherItems: List<WeatherItem>,
    onItemClick: () -> Unit
) {
    Timber.tag("VerticalCollection").i("length ==> ${weatherItems.size}")
    LazyColumn {
        items(
            items = weatherItems,
            itemContent = { item ->
                VerticalListItem(item = item, onItemClick = onItemClick)
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    item: WeatherItem,
    onItemClick: () -> Unit
) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onItemClick() }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Date: ${item.dateText}",
            style = typography.labelLarge
        )
        Text(
            text = "Temperature: ${item.weatherInfo.temperature}",
            style = typography.labelLarge
        )
        Text(
            text = "Pressure: ${item.weatherInfo.pressure}",
            style = typography.labelLarge
        )
        Text(
            text = "Humidity: ${item.weatherInfo.humidity}%",
            style = typography.labelLarge
        )
        Text(
            text = "Description: ${item.weather[0].description}",
            style = typography.labelLarge
        )
    }
}

@Composable
private fun ListItemDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.08f)
    )
}