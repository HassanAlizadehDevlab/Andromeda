package com.android.presentation.ui

import android.os.Bundle
import com.android.presentation.R
import com.android.presentation.common.view.BaseActivity
import com.android.presentation.ui.marvel.MarvelView
import com.android.presentation.ui.marvel.MarvelViewImpl

/**
 * Created by hassanalizadeh on 28,August,2020
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            addFragment(R.id.fragmentContainer, MarvelViewImpl.newInstance())
            // Add fragment here
    }

}