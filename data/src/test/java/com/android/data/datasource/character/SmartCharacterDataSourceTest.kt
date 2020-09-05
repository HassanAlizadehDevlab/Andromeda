package com.android.data.datasource.character

import com.android.common.error.ErrorThrowable
import com.android.common_test.TestUtil
import com.android.data.entity.dao.CharacterDao
import com.android.data.network.DataServiceCharacter
import com.android.data.repository.datasource.character.SmartCharacterDataSource
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
class SmartCharacterDataSourceTest {

    @Mock
    private lateinit var dataService: DataServiceCharacter

    @Mock
    private lateinit var characterDao: CharacterDao
    private lateinit var smartCharacterDataSource: SmartCharacterDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        smartCharacterDataSource = spy(
            SmartCharacterDataSource(
                dataService,
                characterDao
            )
        )
    }

    @Test
    fun `get characters`() {
        // GIVEN
        doReturn(Flowable.just(mutableListOf(TestUtil.firstCharacterFromRemote())))
            .whenever(characterDao)
            .selectAll()

        // WHEN
        smartCharacterDataSource.characters()
            .test()
            .assertComplete()

        // THEN
        verify(characterDao).selectAll()
    }

    @Test
    fun `load characters with result`() {
        // GIVEN
        val expectedPage = 1
        val expectedTotalCount = TestUtil.charactersFromRemote().data.total
        doReturn(Single.just(TestUtil.charactersFromRemote()))
            .whenever(dataService).characters(anyMap())

        // WHEN
        smartCharacterDataSource.loadCharacters()
            .test()
            .assertComplete()

        // THEN
        verify(dataService).characters(
            argThat {
                this[offset] == expectedPage.toString()
            })
        verify(characterDao).deleteAll()
        verify(characterDao).insert(
            argThat {
                this.size == 20 && this[0].id == TestUtil.firstCharacterFromRemote().id
            }
        )
        assert(smartCharacterDataSource._totalCount == expectedTotalCount)
    }

    @Test
    fun `load characters on error`() {
        // GIVEN
        doReturn(Single.error<ErrorThrowable>(TestUtil.error()))
            .whenever(dataService).characters(anyMap())

        // WHEN
        smartCharacterDataSource.loadCharacters()
            .test()
            .assertNotComplete()

        // THEN
        verify(characterDao, never()).insert(anyList())
    }

    @Test
    fun `load more with result`() {
        // GIVEN
        val page = 3
        doReturn(Single.just(TestUtil.charactersFromRemote())).whenever(dataService)
            .characters(anyMap())

        // WHEN
        smartCharacterDataSource.loadCharacters()
            .test()
            .assertComplete()
        smartCharacterDataSource.loadMoreCharacters()
            .test()
            .assertComplete()
        smartCharacterDataSource.loadMoreCharacters()
            .test()
            .assertComplete()

        // THEN
        verify(dataService, atLeastOnce()).characters(anyMap())
        assert(smartCharacterDataSource._page == page)
    }

    companion object {
        const val offset = "offset"
    }

}