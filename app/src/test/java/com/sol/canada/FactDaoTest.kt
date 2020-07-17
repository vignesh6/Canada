package com.sol.canada

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.sol.canada.data.AppDatabase
import com.sol.canada.ui.countryfactdetails.data.FactDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Class responsible for testing FactDao
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FactDaoTest {
    private lateinit var database: AppDatabase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }
    //Clear facts table and check for empty table
    @Test
    fun clearFactsSuccess(){
        mainCoroutineRule.runBlockingTest {
            database.factDao().clearData()
            testScope.launch {
                val facts = database.factDao().getFacts()
                Truth.assertThat(facts.value!!.isEmpty()).isTrue()
            }
        }
    }

    //insert in to fact table and check for facts table list size and not empty
    @Test
    fun insertSuccess() {
        val factsList = listOf(
            FactDetail(
                id = 0,
                description = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
                imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",
                title = "Beavers"
            ),
            FactDetail(
                id = 0,
                description = null,
                imageHref = "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
                title = "Flag"
            )
        )
        mainCoroutineRule.runBlockingTest {
            database.factDao().insertAll(factsList)
            testScope.launch {
                val facts = database.factDao().getFacts()
                Truth.assertThat(facts.value!!.isNotEmpty()).isTrue()
                Truth.assertThat(facts.value!!.size).isEqualTo(2)
            }

        }
    }

    @After
    fun closeDb() = database.close()
}