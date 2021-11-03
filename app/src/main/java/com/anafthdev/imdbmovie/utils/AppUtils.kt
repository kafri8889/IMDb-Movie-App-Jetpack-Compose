package com.anafthdev.imdbmovie.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.widget.Toast

object AppUtils {
	
	object PreferencesKey {
		const val API_KEY = "api_key"
	}
	
//	infix fun Int.greaterThanEqualTo(other: Int): Boolean = this >= other
//
//	infix fun Int.greaterThan(other: Int): Boolean = this > other
//
//	infix fun Int.lessThanEqualTo(other: Int): Boolean = this <= other
//
//	infix fun Int.lessThan(other: Int): Boolean = this < other
	
//	fun Activity.screenWidth(): Int {
//		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//			val metrics = this.windowManager.currentWindowMetrics
//			val insets = metrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
//			(metrics.bounds.width() - insets.left - insets.right)
//		} else {
//			val displayMetrics = DisplayMetrics()
//			this.windowManager.defaultDisplay.getMetrics(displayMetrics)
//			displayMetrics.widthPixels
//		}
//	}
//
//	fun Activity.screenHeight(): Int {
//		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//			val metrics = this.windowManager.currentWindowMetrics
//			val insets = metrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
//			(metrics.bounds.height() - insets.top - insets.bottom)
//		} else {
//			val displayMetrics = DisplayMetrics()
//			this.windowManager.defaultDisplay.getMetrics(displayMetrics)
//			displayMetrics.heightPixels
//		}
//	}
	
	fun Context.isConnectedToInternet(): Boolean {
		val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val netCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			if (netCapabilities != null) {
				return netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or
						netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
			}
		} else {
			connectivityManager.allNetworks.forEach {
				connectivityManager.getNetworkInfo(it)?.apply {
					return (type == ConnectivityManager.TYPE_WIFI) or
							(type == ConnectivityManager.TYPE_MOBILE)
				}
			}
		}
		
		return false
	}
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, this.toString(), length).show()
}