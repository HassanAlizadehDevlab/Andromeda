package com.android.presentation.ui.marvel.adapter

import com.android.presentation.adapter.ActionType
import com.android.presentation.adapter.BaseAction

/**
 * Created by hassanalizadeh on 07,September,2020
 */
class CharacterAction (val data: Int): BaseAction {

    override fun getType(): ActionType = ActionType.COMICS

}