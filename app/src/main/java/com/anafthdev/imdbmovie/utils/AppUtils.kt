package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.widget.Toast

object AppUtils {
	
	object PreferencesKey {
		const val API_KEY = "api_key"
	}
	
	infix fun Int.greaterThanEqualTo(other: Int): Boolean = this >= other
	
	infix fun Int.greaterThan(other: Int): Boolean = this > other
	
	infix fun Int.lessThanEqualTo(other: Int): Boolean = this <= other
	
	infix fun Int.lessThan(other: Int): Boolean = this < other
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, this.toString(), length).show()
}