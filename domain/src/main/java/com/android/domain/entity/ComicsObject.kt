package com.android.domain.entity

/**
 * Created by hassanalizadeh on 05,September,2020
 */
data class ComicsObject(
    val totalCount: Int,
    val items: List<ComicObject>
): DomainObject