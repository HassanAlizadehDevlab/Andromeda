package com.android.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 09,September,2020
 */
class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PreferencesHelper {

    override fun setLastTimeVisited(time: String) {
        sharedPreferences.edit { putString(VISITED_TIME, time) }
    }

    override fun getLastTimeVisited(): String {
        return sharedPreferences.getString(VISITED_TIME, "").toString()
    }

    companion object {
        const val VISITED_TIME = "VISITED_TIME"
    }

}