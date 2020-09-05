package com.android.presentation.common.navigator

import androidx.fragment.app.FragmentManager
import com.android.presentation.ui.MainActivityModule
import javax.inject.Inject
import javax.inject.Named

/**
 * Handle all navigation in here.
 *
 * @param fragmentManager is [MainActivity]'s fragmentManager.
 */
class Navigator @Inject constructor(
    @Named(MainActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    val fragmentManager: FragmentManager
)