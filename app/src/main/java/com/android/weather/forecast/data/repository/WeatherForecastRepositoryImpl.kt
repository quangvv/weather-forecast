package com.android.weather.forecast.data.repository

import android.content.Context
import com.android.weather.forecast.R
import com.android.weather.forecast.common.connectivity.ConnectivityDetector
import com.android.weather.forecast.common.dispatcher.DispatcherProvider
import com.android.weather.forecast.data.data.local.sharedprefs.source.PreferenceSource
import com.android.weather.forecast.data.data.remote.source.RemoteDataSource
import com.android.weather.forecast.data.model.Resource
import com.android.weather.forecast.domain.model.WeatherItem
import com.android.weather.forecast.domain.repository.WeatherForecastRepository
import com.android.weather.forecast.utils.SOMETHING_WENT_WRONG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val context: Context,
    private val connect: ConnectivityDetector,
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: PreferenceSource
) : WeatherForecastRepository {

    override suspend fun getWeatherForecast(city: String): Flow<Resource<List<WeatherItem>>> =
        flow {
            emit(Resource.loading(null))
            remoteDataSource.getWeatherForecastData(city).run {
                if (connect.isConnected()) {
                    try {
                        this.getOrNull()?.let { value ->
                            emit(Resource.success(value))
                        }
                        this.exceptionOrNull()?.let { error ->
                            error.message?.let {
                                emit(Resource.error(it, null))
                            }
                        }
                    } catch (t: Throwable) {
                        emit(Resource.error(SOMETHING_WENT_WRONG, null))
                    }
                } else {
                    emit(Resource.error(context.getString(R.string.failed_network_msg), null))
                }
            }
        }.flowOn(dispatcherProvider.io())
}