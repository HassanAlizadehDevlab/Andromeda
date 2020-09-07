package com.android.presentation.ui.marvel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.android.common.extension.orZero
import com.android.domain.entity.CharactersObject
import com.android.domain.entity.ComicsObject
import com.android.domain.usecase.character.GetCharactersUseCase
import com.android.domain.usecase.character.LoadMoreCharactersUseCase
import com.android.domain.usecase.character.RefreshCharactersUseCase
import com.android.domain.usecase.comic.GetComicsUseCase
import com.android.domain.usecase.comic.LoadMoreComicsUseCase
import com.android.domain.usecase.comic.RefreshComicsUseCase
import com.android.domain.usecase.invoke
import com.android.presentation.adapter.BaseAction
import com.android.presentation.adapter.LoadMoreState
import com.android.presentation.common.view.BasePresenter
import com.android.presentation.ui.marvel.adapter.CharacterAction
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
class MarvelPresenterImpl @Inject constructor(
    private val view: MarvelView?,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getComicsUseCase: GetComicsUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val refreshComicsUseCase: RefreshComicsUseCase,
    private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase,
    private val loadMoreComicsUseCase: LoadMoreComicsUseCase
) : BasePresenter(), MarvelPresenter {

    @VisibleForTesting
    val _characters = MutableLiveData<CharactersObject>()

    @VisibleForTesting
    val _comics = MutableLiveData<ComicsObject>()

    @VisibleForTesting
    var _totalCharacters: Int = 0

    @VisibleForTesting
    var _totalComics: Int = 0


    init {
        _characters.observeForever {
            this._totalCharacters = it.totalCount
            view?.setCharacters(it.items)
        }

        _comics.observeForever {
            this._totalComics = it.totalCount
            view?.setComics(it.items)
        }

        getCharactersUseCase.invoke()
            .subscribe({
                _characters.value = it
            }, {})
            .track()

        getComicsUseCase.invoke()
            .subscribe({
                _comics.value = it
            }, {})
            .track()
    }

    override fun refresh() {
        view?.loading(true)
        refreshCharactersUseCase.invoke()
            .doOnEvent { view?.loading(false) }
            .onError()
            .subscribe({}, {})
            .track()
    }

    override fun loadMoreCharacters(loadMoreObservable: PublishSubject<LoadMoreState>) {
        loadMoreObservable.subscribe {
            if (it == LoadMoreState.LOAD)
                if (_characters.value?.items?.size.orZero() < _totalCharacters.orZero())
                    loadMoreCharacters { loadMoreObservable.onNext(LoadMoreState.NOT_LOAD) }
                else
                    loadMoreObservable.onNext(LoadMoreState.FINISH)
        }.track()
    }

    override fun loadMoreComics(loadMoreObservable: PublishSubject<LoadMoreState>) {
        loadMoreObservable.subscribe {
            if (it == LoadMoreState.LOAD)
                if (_comics.value?.items?.size.orZero() < _totalComics.orZero())
                    loadMoreComics { loadMoreObservable.onNext(LoadMoreState.NOT_LOAD) }
                else
                    loadMoreObservable.onNext(LoadMoreState.FINISH)
        }.track()
    }

    private fun loadMoreComics(onNext: () -> Unit) {
        loadMoreComicsUseCase.invoke()
            .doOnEvent { onNext.invoke() }
            .onError()
            .subscribe({}, {})
            .track()
    }

    private fun loadMoreCharacters(onNext: () -> Unit) {
        loadMoreCharactersUseCase.invoke()
            .doOnEvent { onNext.invoke() }
            .onError()
            .subscribe({}, {})
            .track()
    }

    private fun refreshComics(characterId: Int) {
        view?.comicsLoading(true)
        refreshComicsUseCase.invoke(characterId)
            .doOnEvent { view?.comicsLoading(false) }
            .onError()
            .subscribe({}, {})
            .track()
    }

    override fun observeClicks(actions: Observable<BaseAction>) {
        actions.subscribe {
            when (it) {
                is CharacterAction -> refreshComics(it.data)
                else -> {
                }
            }
        }.track()
    }

    override fun onDestroy() {
        onCleared()
    }

}