package com.android.presentation.common.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by hassanalizadeh on 28,August,2020
 */
fun FragmentManager.replaceFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    this.beginTransaction()
        .replace(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commit()
}

fun FragmentManager.addFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    this.beginTransaction()
        .add(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commit()
}

fun FragmentManager.detachFragment(fragment: Fragment, popBackStack: Boolean = false) {
    this.beginTransaction()
        .detach(fragment)
        .commit()
    if (popBackStack) popBackStack()
}