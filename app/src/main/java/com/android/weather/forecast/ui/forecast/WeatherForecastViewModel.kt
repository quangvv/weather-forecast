package com.android.weather.forecast.ui.forecast

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.android.weather.forecast.data.model.Resource
import com.android.weather.forecast.domain.model.WeatherItem
import com.android.weather.forecast.domain.usecase.GetWeatherListUseCase
import com.android.weather.forecast.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val useCase: GetWeatherListUseCase
) : BaseViewModel<Any?>() {

    private val _stateFlow = MutableStateFlow<Resource<List<WeatherItem>>>(Resource.success(null))
    val stateFlow: StateFlow<Resource<List<WeatherItem>>>
        get() = _stateFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()

    private val isSearchKey: MutableState<String?> = mutableStateOf(null)

    fun searchWeatherForecast(searchKey: String) {
        viewModelScope.launch {
            isSearchKey.value = searchKey
            _isLoading.emit(true)
            useCase.getWeatherForecast(searchKey).collect {
                _stateFlow.tryEmit(it)
            }
            _isLoading.emit(false)
        }
    }

    fun refresh() {
        isSearchKey.value?.let { searchWeatherForecast(it) }
    }
}