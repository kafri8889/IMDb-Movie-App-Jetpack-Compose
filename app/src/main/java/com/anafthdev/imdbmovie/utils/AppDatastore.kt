package com.anafthdev.imdbmovie.utils

import android.content.Context
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
	
	private val Context.datastore by preferencesDataStore("AppDatastore")
	private val API_KEY = stringPreferencesKey(AppUtils.PreferencesKey.API_KEY)
	
	suspend fun setApiKey(s: String) = context.datastore.edit {
		it[API_KEY] = s
	}
	
	val getApiKey: Flow<String> = context.datastore.data
		.catch { exception ->
			if (exception is IOException) emit(emptyPreferences())
			else throw exception
		}.map { preferences -> preferences[API_KEY] ?: throw RuntimeException("no API key") }
	
}