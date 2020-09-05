package com.android.data.datasource.comic

import com.android.common.error.ErrorThrowable
import com.android.common_test.TestUtil
import com.android.data.entity.dao.ComicDao
import com.android.data.network.DataServiceComic
import com.android.data.repository.datasource.comic.SmartComicDataSource
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 30,August,2020
 */
@RunWith(JUnit4::class)
class SmartComicDataSourceTest {

    @Mock
    private lateinit var dataService: DataServiceComic

    @Mock
    private lateinit var comicDao: ComicDao
    private lateinit var smartComicDataSource: SmartComicDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        smartComicDataSource = spy(
            SmartComicDataSource(
                dataService,
                comicDao
            )
        )
    }

    @Test
    fun `get comics`() {
        // GIVEN
        doReturn(Flowable.just(mutableListOf(TestUtil.firstComicFromRemote())))
            .whenever(comicDao)
            .selectAll()

        // WHEN
        smartComicDataSource.comics()
            .test()
            .assertComplete()

        // THEN
        verify(comicDao).selectAll()
    }

    @Test
    fun `load comics with result`() {
        // GIVEN
        val expectedPage = 1
        val characterId = 2433
        val expectedTotalCount = TestUtil.comicsFromRemote().data.total
        doReturn(Single.just(TestUtil.comicsFromRemote()))
            .whenever(dataService).comics(anyInt(), anyMap())

        // WHEN
        smartComicDataSource.loadComics(characterId)
            .test()
            .assertComplete()

        // THEN
        verify(dataService).comics(
            anyInt(),
            argThat {
                this[offset] == expectedPage.toString()
            })
        verify(comicDao).deleteAll()
        verify(comicDao).insert(
            argThat {
                this.size == 12 && this[0].id == TestUtil.firstComicFromRemote().id
            }
        )
        assert(smartComicDataSource._totalCount == expectedTotalCount)
        assert(smartComicDataSource._characterId == characterId)
    }

    @Test
    fun `load comics on error`() {
        // GIVEN
        val characterId = 2433
        doReturn(Single.error<ErrorThrowable>(TestUtil.error()))
            .whenever(dataService).comics(anyInt(), anyMap())

        // WHEN
        smartComicDataSource.loadComics(characterId)
            .test()
            .assertNotComplete()

        // THEN
        verify(comicDao, never()).insert(anyList())
    }

    @Test
    fun `load more with result`() {
        // GIVEN
        val page = 3
        val characterId = 2433
        doReturn(Single.just(TestUtil.comicsFromRemote())).whenever(dataService)
            .comics(anyInt(), anyMap())

        // WHEN
        smartComicDataSource.loadComics(characterId)
            .test()
            .assertComplete()
        smartComicDataSource.loadMoreComics()
            .test()
            .assertComplete()
        smartComicDataSource.loadMoreComics()
            .test()
            .assertComplete()

        // THEN
        verify(dataService, atLeastOnce()).comics(eq(characterId), anyMap())
        assert(smartComicDataSource._page == page)
    }

    companion object {
        const val offset = "offset"
    }

}