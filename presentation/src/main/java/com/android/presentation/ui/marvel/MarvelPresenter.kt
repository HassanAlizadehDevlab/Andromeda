package com.android.presentation.ui.marvel

import com.android.presentation.adapter.BaseAction
import com.android.presentation.adapter.LoadMoreState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
interface MarvelPresenter {

    /**
     * API call to get new characters.
     * */
    fun refresh()

    fun loadMoreCharacters(loadMoreObservable: PublishSubject<LoadMoreState>)

    fun loadMoreComics(loadMoreObservable: PublishSubject<LoadMoreState>)

    /**
     * Observe the recycler view items actions
     * */
    fun observeClicks(actions: Observable<BaseAction>)

    /**
     * Call on fragment onDestroy called.
     * */
    fun onDestroy()

}