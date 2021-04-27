package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.repositories

import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItem
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.responses.ImageResponse
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.other.Resource
import androidx.lifecycle.LiveData

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItme(shoppingItem: ShoppingItem)

    suspend fun observeAllShoppingItmes() : LiveData<List<ShoppingItem>>

    suspend fun observeTotalPrice() : LiveData<Float>

    suspend fun searchForImage(imageQuery: String) : Resource<ImageResponse>
}