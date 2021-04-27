package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.repositories

import `in`.utilitysoftwares.androidtestingapplication.BuildConfig
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingDao
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItem
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.PixabayAPI
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.responses.ImageResponse
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.other.Resource
import androidx.lifecycle.LiveData
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItme(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override suspend fun observeAllShoppingItmes(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override suspend fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery, "21356213-956925d1b43b015dc66d4131f")
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown Error Occurred", null)
            }else{
                Resource.error("Unknown Error Occurred", null)
            }
        }catch (exception : Exception){
            return Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}