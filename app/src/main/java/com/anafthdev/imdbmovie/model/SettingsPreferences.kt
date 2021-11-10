package com.anafthdev.imdbmovie.model

data class SettingsPreferences(
	val title: String,
	val summary: String? = null,
	var checked: Boolean = false,
	val type: PreferencesType = PreferencesType.BASIC
) {
	sealed class PreferencesType(val type: String) {
		object BASIC: PreferencesType("basic")
		object SWITCH: PreferencesType("switch")
	}
}
