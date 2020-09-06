package com.android.presentation.ui.marvel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.common_test.TestUtil
import com.android.common_test.observeOnce
import com.android.data.entity.mapper.map
import com.android.data.entity.mapper.mapCharacters
import com.android.data.entity.mapper.mapComics
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
import com.android.presentation.ui.marvel.adapter.ComicsAction
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

/**
 * Created by hassanalizadeh on 06,September,2020
 */
@RunWith(JUnit4::class)
class MarvelPresenterImplTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val view: MarvelView = mock()
    private val getCharactersUseCase: GetCharactersUseCase = mock()
    private val getComicsUseCase: GetComicsUseCase = mock()
    private val refreshCharactersUseCase: RefreshCharactersUseCase = mock()
    private val refreshComicsUseCase: RefreshComicsUseCase = mock()
    private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase = mock()
    private val loadMoreComicsUseCase: LoadMoreComicsUseCase = mock()
    private lateinit var presenter: MarvelPresenterImpl


    @Test
    fun `get characters`() {
        //GIVEN
        val characterTotalCount = 20
        dataExistsForGetCharacters(characterTotalCount)
        dataExistsForGetComics()
        createPresenter()

        //WHEN
        // getCharactersUseCase call automatically at first

        //THEN
        verify(getCharactersUseCase).invoke()
        verify(view).setCharacters(
            argThat {
                this[0].id == TestUtil.firstCharacterFromRemote().id
            }
        )
        assert(presenter._totalCharacters == characterTotalCount)
    }

    @Test
    fun `refresh characters`() {
        //GIVEN
        dataExistsForGetCharacters()
        dataExistsForGetComics()
        dataExistsForRefreshCharacters()
        createPresenter()

        //WHEN
        presenter.refresh()

        //THEN
        verify(view).loading(true)
        verify(refreshCharactersUseCase).invoke()
        verify(view).loading(false)
    }

    @Test
    fun `load more characters`() {
        //GIVEN
        val characterTotalCount = 25
        val loadMoreObservable = spy(PublishSubject.create<LoadMoreState>())
        val charactersObserver = mock<(CharactersObject) -> Unit>()
        dataExistsForGetCharacters(characterTotalCount)
        dataExistsForGetComics()
        dataExistsForLoadMoreCharacters()
        createPresenter()
        presenter._characters.observeOnce(charactersObserver)
        presenter.loadMoreCharacters(loadMoreObservable)

        //WHEN
        loadMoreObservable.onNext(LoadMoreState.LOAD)

        //THEN
        verify(loadMoreCharactersUseCase).invoke()
        verify(loadMoreObservable, atLeastOnce()).onNext(LoadMoreState.NOT_LOAD)
    }

    @Test
    fun `get comics`() {
        //GIVEN
        val comicsTotalCount = 20
        dataExistsForGetCharacters()
        dataExistsForGetComics(comicsTotalCount)
        createPresenter()

        //WHEN
        // getComicsUseCase call automatically at first

        //THEN
        verify(getComicsUseCase).invoke()
        verify(view).setComics(
            argThat {
                this[0].id == TestUtil.firstComicFromRemote().id
            }
        )
        assert(presenter._totalComics == comicsTotalCount)
    }

    @Test
    fun `refresh comics`() {
        //GIVEN
        val characterId = 4322
        val clickObservable = PublishSubject.create<BaseAction>()
        dataExistsForGetCharacters()
        dataExistsForGetComics()
        dataExistsForRefreshComics()
        createPresenter()
        presenter.observeClicks(clickObservable)

        //WHEN
        clickObservable.onNext(ComicsAction(characterId))

        //THEN
        verify(view).comicsLoading(true)
        verify(refreshComicsUseCase).invoke(characterId)
        verify(view).comicsLoading(false)
    }

    @Test
    fun `load more comics`() {
        //GIVEN
        val comicsTotalCount = 25
        val loadMoreObservable = spy(PublishSubject.create<LoadMoreState>())
        val comicsObserver = mock<(ComicsObject) -> Unit>()
        dataExistsForGetCharacters(comicsTotalCount)
        dataExistsForGetComics()
        dataExistsForLoadMoreComics()
        createPresenter()
        presenter._comics.observeOnce(comicsObserver)
        presenter.loadMoreComics(loadMoreObservable)

        //WHEN
        loadMoreObservable.onNext(LoadMoreState.LOAD)

        //THEN
        verify(loadMoreComicsUseCase).invoke()
        verify(loadMoreObservable, atLeastOnce()).onNext(LoadMoreState.NOT_LOAD)
    }

    //GIVEN
    //WHEN
    //THEN

    private fun dataExistsForGetCharacters(characterTotalCount: Int = 20) {
        doReturn(
            Flowable.just(
                CharactersObject(
                    totalCount = characterTotalCount,
                    TestUtil.charactersFromRemote().data.characters.mapCharacters().map()
                )
            )
        ).whenever(getCharactersUseCase).invoke()
    }

    private fun dataExistsForRefreshCharacters() {
        doReturn(Completable.complete()).whenever(refreshCharactersUseCase).invoke()
    }

    private fun dataExistsForLoadMoreCharacters() {
        doReturn(Completable.complete()).whenever(loadMoreCharactersUseCase).invoke()
    }

    private fun dataExistsForGetComics(comicsTotalCount: Int = 20) {
        doReturn(
            Flowable.just(
                ComicsObject(
                    totalCount = comicsTotalCount,
                    TestUtil.comicsFromRemote().data.comics.mapComics().mapComics()
                )
            )
        ).whenever(getComicsUseCase).invoke()
    }

    private fun dataExistsForRefreshComics() {
        doReturn(Completable.complete()).whenever(refreshComicsUseCase).invoke(anyInt())
    }

    private fun dataExistsForLoadMoreComics() {
        doReturn(Completable.complete()).whenever(loadMoreComicsUseCase).invoke()
    }

    private fun createPresenter() {
        presenter = MarvelPresenterImpl(
            view,
            getCharactersUseCase,
            getComicsUseCase,
            refreshCharactersUseCase,
            refreshComicsUseCase,
            loadMoreCharactersUseCase,
            loadMoreComicsUseCase
        )
    }

}