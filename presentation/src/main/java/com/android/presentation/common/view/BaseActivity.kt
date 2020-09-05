package com.android.presentation.common.view

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.android.presentation.common.extension.addFragment
import com.android.presentation.common.extension.detachFragment
import com.android.presentation.common.extension.toast
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base class for all activities.
 */
open class BaseActivity : DaggerAppCompatActivity() {

    protected fun addFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        supportFragmentManager.addFragment(containerViewId, fragment, addToBackStack)
    }

    protected fun detachFragment(fragment: Fragment, popBackStack: Boolean = false) {
        supportFragmentManager.detachFragment(fragment, popBackStack)
    }

    protected fun showMessage(@StringRes resourceId: Int) {
        showMessage(getString(resourceId))
    }

    protected fun showMessage(message: String) {
        toast(message)
    }

}