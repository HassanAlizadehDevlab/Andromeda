package com.android.presentation.ui.marvel.adapter

import android.view.View
import com.android.domain.entity.CharacterObject
import com.android.presentation.adapter.BaseViewHolder
import com.android.presentation.adapter.ViewTypeHolder
import com.android.presentation.common.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_character.view.*

/**
 * Created by hassanalizadeh on 07,September,2020
 */
class CharacterViewHolder(
    override val containerView: View
) : BaseViewHolder<CharacterObject>(containerView), LayoutContainer {

    override fun getType(): Int = ViewTypeHolder.CHARACTER_VIEW

    override fun bind(data: CharacterObject?) {
        data ?: return

        // Set data to view
        Glide.with(containerView)
            .load(data.thumbnail)
            .transform(RoundedCorners(Utils.convertDpToPx(containerView.context, 8f).toInt()))
            .into(containerView.characterImage)

        containerView.characterName.text = data.name

        // Handle click listener
        containerView.character.setOnClickListener {
            mSubject.onNext(CharacterAction(data.id))
        }
    }

}