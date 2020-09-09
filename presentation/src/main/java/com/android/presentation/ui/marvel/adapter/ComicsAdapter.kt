package com.android.presentation.ui.marvel.adapter

import android.view.ViewGroup
import com.android.domain.entity.ComicObject
import com.android.presentation.adapter.*
import com.android.presentation.common.extension.inflate

/**
 * Created by hassanalizadeh on 31,August,2020
 */
class ComicsAdapter(
    private val listener: (BaseViewHolder<*>) -> Unit
) : BaseRecyclerAdapter(CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = parent.inflate(viewType)
        val holder = when (viewType) {
            ViewTypeHolder.COMIC_VIEW -> ComicViewHolder(view)
            ViewTypeHolder.LOAD_MORE_VIEW -> LoadMoreViewHolder(view)
            else -> EmptyViewHolder(view)
        }
        listener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val data = mItems[position]
        when (holder.getType()) {
            ViewTypeHolder.COMIC_VIEW -> (holder as ComicViewHolder).bind(data as? ComicObject)
            ViewTypeHolder.LOAD_MORE_VIEW -> (holder as LoadMoreViewHolder).bind(Unit)
        }
    }

    companion object {
        val CONFIG = Config.Builder()
            .setPreFetch(10)
            .setScreenSize(2)
            .build()
    }

}