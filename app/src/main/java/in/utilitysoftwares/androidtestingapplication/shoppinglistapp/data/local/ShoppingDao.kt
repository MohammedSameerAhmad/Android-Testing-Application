package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingDao: ShoppingDao)

    @Delete
    suspend fun deleteShoppingItem (shoppingDao: ShoppingDao)

    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM( price * amount) FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>
}