package com.android.presentation.ui.marvel.adapter

import android.view.ViewGroup
import com.android.domain.entity.CharacterObject
import com.android.presentation.adapter.*
import com.android.presentation.common.extension.inflate

/**
 * Created by hassanalizadeh on 31,August,2020
 */
class CharactersAdapter(
    private val listener: (BaseViewHolder<*>) -> Unit
) : BaseRecyclerAdapter(CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = parent.inflate(viewType)
        val holder = when (viewType) {
            ViewTypeHolder.CHARACTER_VIEW -> CharacterViewHolder(view)
            ViewTypeHolder.LOAD_MORE_VIEW -> LoadMoreViewHolder(view)
            else -> EmptyViewHolder(view)
        }
        listener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        super.onBindViewHolder(holder, position)

        val data = mItems[position]
        when (holder.getType()) {
            ViewTypeHolder.CHARACTER_VIEW -> (holder as CharacterViewHolder).bind(data as? CharacterObject)
            ViewTypeHolder.LOAD_MORE_VIEW -> (holder as LoadMoreViewHolder).bind(Unit)
        }
    }

    companion object {
        val CONFIG = Config.Builder()
            .setPreFetch(5)
            .setScreenSize(3)
            .build()
    }

}