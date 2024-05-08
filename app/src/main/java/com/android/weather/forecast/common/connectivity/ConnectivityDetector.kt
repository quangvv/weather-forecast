package com.android.weather.forecast.common.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityDetector {
    fun isConnected(): Boolean
    fun networkStatus() : Flow<NetworkStatus>
}

sealed class NetworkStatus {
    object AVAILABLE : NetworkStatus()
    object UNAVAILABLE : NetworkStatus()
}