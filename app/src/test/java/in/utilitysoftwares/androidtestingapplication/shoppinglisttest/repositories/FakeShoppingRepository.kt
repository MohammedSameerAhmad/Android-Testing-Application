package `in`.utilitysoftwares.androidtestingapplication.shoppinglisttest.repositories

import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItem
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.responses.ImageResponse
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.other.Resource
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.repositories.ShoppingRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeShoppingRepository : ShoppingRepository{

    private val shoppingListItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingListItems)

    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingListItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice() : Float {
        return shoppingListItems.sumByDouble {
            it.price.toDouble()
        }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingListItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItme(shoppingItem: ShoppingItem) {
        shoppingListItems.remove(shoppingItem)
        refreshLiveData()
    }

    override suspend fun observeAllShoppingItmes(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override suspend fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return  if (shouldReturnNetworkError){
            Resource.error("Error", null)
        }else{
            Resource.success(ImageResponse(listOf(), 0,0))
        }
    }

}