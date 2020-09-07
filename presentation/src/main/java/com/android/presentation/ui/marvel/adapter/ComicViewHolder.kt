package com.android.presentation.ui.marvel.adapter

import android.view.View
import com.android.domain.entity.ComicObject
import com.android.presentation.adapter.BaseViewHolder
import com.android.presentation.adapter.ViewTypeHolder
import com.android.presentation.common.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_comic.view.*

/**
 * Created by hassanalizadeh on 07,September,2020
 */
class ComicViewHolder(
    override val containerView: View
) : BaseViewHolder<ComicObject>(containerView), LayoutContainer {

    override fun getType(): Int = ViewTypeHolder.COMIC_VIEW

    override fun bind(data: ComicObject?) {
        data ?: return

        // Set data to view
        Glide.with(containerView)
            .load(data.thumbnail)
            .transform(RoundedCorners(Utils.convertDpToPx(containerView.context, 8f).toInt()))
            .into(containerView.comicImage)

        containerView.comicTitle.text = data.title
        containerView.comicDescription.text = data.description
    }

}