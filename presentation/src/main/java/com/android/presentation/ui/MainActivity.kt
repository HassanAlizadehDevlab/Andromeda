package com.android.presentation.ui

import android.os.Bundle
import com.android.presentation.R
import com.android.presentation.common.view.BaseActivity

/**
 * Created by hassanalizadeh on 28,August,2020
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // if (savedInstanceState == null)
            // Add fragment here
    }

}