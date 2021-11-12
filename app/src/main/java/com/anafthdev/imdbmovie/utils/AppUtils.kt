package com.anafthdev.imdbmovie.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowInsets
import android.widget.Toast

object AppUtils {
	
	object PreferencesKey {
		const val API_KEY = "api_key"
		const val USE_SAMPLE_DATA = "use_sample_data"
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
	
	fun <T> Collection<T>.get(predicate: (T) -> Boolean): T? {
		var result: T? = null
		for (item in this) {
			if (predicate(item)) {
				result = item
				break
			}
		}
		
		return result
	}
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, this.toString(), length).show()
}