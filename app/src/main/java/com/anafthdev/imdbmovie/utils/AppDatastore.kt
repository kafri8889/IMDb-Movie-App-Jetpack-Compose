package com.anafthdev.imdbmovie.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AppDatastore @Inject constructor(private val context: Context) {
	
	private val API_KEY = stringPreferencesKey(AppUtils.PreferencesKey.API_KEY)
	private val USE_SAMPLE_DATA = booleanPreferencesKey(AppUtils.PreferencesKey.USE_SAMPLE_DATA)
	
	suspend fun setApiKey(s: String) = context.datastore.edit {
		it[API_KEY] = s
	}
	
	suspend fun setUseSampleData(use: Boolean) = context.datastore.edit {
		it[USE_SAMPLE_DATA] = use
	}
	
	val getApiKey: Flow<String> = context.datastore.data
		.catch { exception ->
			if (exception is IOException) emit(emptyPreferences())
			else throw exception
		}.map { preferences -> preferences[API_KEY] ?: ERROR_NO_API_KEY }
	
	val isUseSampleData: Flow<Boolean> = context.datastore.data
		.catch { exception ->
			if (exception is IOException) emit(emptyPreferences())
			else throw exception
		}.map { preferences -> preferences[USE_SAMPLE_DATA] ?: false }
	
	companion object {
		private val Context.datastore by preferencesDataStore("AppDatastore")
		const val ERROR_NO_API_KEY = "no_api_key"
	}
}