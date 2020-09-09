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

    override fun setLastTimeVisited(timestamp: Long) {
        sharedPreferences.edit { putLong(VISITED_TIME, timestamp) }
    }

    override fun getLastTimeVisited(): Long {
        return sharedPreferences.getLong(VISITED_TIME, 0)
    }

    companion object {
        const val VISITED_TIME = "VISITED_TIME"
    }

}