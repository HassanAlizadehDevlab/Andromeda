package com.android.presentation.adapter

import android.view.View

/**
 * Created by hassanalizadeh on 04,March,2019
 */
class LoadMoreViewHolder(val view: View) : BaseViewHolder<Unit>(view) {

    override fun getType(): Int = ViewTypeHolder.LOAD_MORE_VIEW

    override fun bind(data: Unit?) {

    }

}