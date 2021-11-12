package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NetworkUtil @Inject constructor(base: Context) {
	
	private val _isNetworkAvailable = MutableStateFlow(false)
	val isNetworkAvailable = _isNetworkAvailable.asStateFlow()
	
	private val connectivityManager = base.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	
	init {
		connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
			override fun onAvailable(network: Network) {
				super.onAvailable(network)
				_isNetworkAvailable.value = true
			}
			
			override fun onLost(network: Network) {
				super.onLost(network)
				_isNetworkAvailable.value = false
			}
			
			override fun onUnavailable() {
				super.onUnavailable()
				_isNetworkAvailable.value = false
			}
		})
	}
}