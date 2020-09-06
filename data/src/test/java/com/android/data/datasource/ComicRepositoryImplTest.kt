package com.android.data.datasource

import com.android.common_test.TestUtil
import com.android.data.entity.mapper.mapComics
import com.android.data.repository.ComicRepositoryImpl
import com.android.data.repository.datasource.comic.ComicDataSource
import com.android.domain.repository.ComicRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 30,August,2020
 */
@RunWith(JUnit4::class)
class ComicRepositoryImplTest {

    @Mock
    private lateinit var dataSource: ComicDataSource
    private lateinit var repository: ComicRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = ComicRepositoryImpl(dataSource)
    }

    @Test
    fun `get characters`() {
        //GIVEN
        doReturn(Flowable.just(Pair(0, TestUtil.comicsFromRemote().data.comics.mapComics())))
            .whenever(dataSource).comics()

        //WHEN
        repository.comics()
            .test()
            .assertValue {
                it.items[0].id == TestUtil.firstComicFromRemote().id
            }
            .assertComplete()

        //THEN
        verify(dataSource).comics()
    }

    @Test
    fun `load comics`() {
        //GIVEN
        val characterId = 4233
        doReturn(Completable.complete()).whenever(dataSource).loadComics(anyInt())

        //WHEN
        repository.loadComics(characterId)
            .test()
            .assertComplete()

        //THEN
        verify(dataSource).loadComics(characterId)
    }

    @Test
    fun `load more comics`() {
        //GIVEN
        doReturn(Completable.complete()).whenever(dataSource).loadMoreComics()

        //WHEN
        repository.loadMoreComics()
            .test()
            .assertComplete()

        //THEN
        verify(dataSource).loadMoreComics()
    }

}