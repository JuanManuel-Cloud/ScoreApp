package com.moni.scoreapp.data.local.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import com.moni.scoreapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ScoreDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ScoreDatabase
    private lateinit var dao: ScoreDao
    private lateinit var mockRecordItem: RecordItem

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ScoreDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.scoreDao()

        mockRecordItem = RecordItem(
            id = 1,
            name = "Fernando",
            lastname = "Alonso",
            dni = "12121212",
            email = "fernando@f1.com",
            gender = Genders.MALE,
            status = RecordStatus.APPROVED,
            createdDate = Instant.ofEpochSecond(1620000000)
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertRecordItem() = runBlocking {
        dao.insertRecord(mockRecordItem)

        val allRecordItems = dao.getAllRecords().getOrAwaitValue()

        assertThat(allRecordItems).contains(mockRecordItem)
    }

    @Test
    fun deleteShoppingItem() = runBlocking {
        dao.insertRecord(mockRecordItem)
        dao.deleteRecord(mockRecordItem)

        val allRecordItemS = dao.getAllRecords().getOrAwaitValue()

        assertThat(allRecordItemS).doesNotContain(mockRecordItem)
    }

    @Test
    fun getRecordsByDni() = runBlocking {
        dao.insertRecord(mockRecordItem)

        val recordItems = dao.getRecordsByDniNameOrLastname("12121212").getOrAwaitValue()

        assertThat(recordItems).contains(mockRecordItem)
    }

    @Test
    fun getRecordsByName() = runBlocking {
        val mockRecordItem2 = mockRecordItem.copy(id = 2, lastname = "Cavenaghi")
        dao.insertRecord(mockRecordItem)
        dao.insertRecord(mockRecordItem2)

        val recordItems = dao.getRecordsByDniNameOrLastname("Fernando").getOrAwaitValue()

        assertThat(recordItems).contains(mockRecordItem)
        assertThat(recordItems).contains(mockRecordItem2)
    }
}