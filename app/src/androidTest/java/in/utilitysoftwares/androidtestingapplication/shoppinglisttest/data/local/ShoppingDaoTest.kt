package `in`.utilitysoftwares.androidtestingapplication.shoppinglisttest.data.local

import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingDao
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItem
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItemDB
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ShoppingDaoTest {

    private lateinit var dataBase : ShoppingItemDB
    private lateinit var dao : ShoppingDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDB::class.java
        ).allowMainThreadQueries().build()
        dao = dataBase.shoppingDao()
    }


    @Test
    fun insertShoppingItemInDB() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "",1)
        dao.insertShoppingItem(shoppingItem)

        var shoppingListObserver = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(shoppingListObserver).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItemFromDB() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "",1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        var shoppingListObserver = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(shoppingListObserver).doesNotContain(shoppingItem)

    }

    @Test
    fun observeTotalPriceSum()= runBlockingTest {
        val shoppingItem1 = ShoppingItem("name", 2, 10f, "",1)
        val shoppingItem2 = ShoppingItem("name", 3, 5.5f, "",2)
        val shoppingItem3 = ShoppingItem("name", 0, 1f, "",3)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        var shoppingTotalPriceSum = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(shoppingTotalPriceSum).isEqualTo(2 * 10f + 3 * 5.5f)
    }

    @After
    fun tearDown(){
        dataBase.close()
    }
}