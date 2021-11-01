package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.widget.Toast

object AppUtils {
	
	object PreferencesKey {
		const val API_KEY = "api_key"
	}
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, this.toString(), length).show()
}