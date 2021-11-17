package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import org.joda.time.DateTime
import org.joda.time.LocalDate

object AppUtils {
	
	object PreferencesKey {
		const val API_KEY = "api_key"
		const val USE_SAMPLE_DATA = "use_sample_data"
	}
	
	fun getFewDaysAheadInMillis(currentTime: Long, days: Int): Long {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			LocalDate(currentTime).plusDays(days).toDate().time
		} else DateTime(currentTime).plusDays(days).millis
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