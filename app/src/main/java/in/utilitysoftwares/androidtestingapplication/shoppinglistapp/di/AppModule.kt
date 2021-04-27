package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.di

import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.other.Constants.BASE_URL
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.other.Constants.DATABASE_NAME
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.local.ShoppingItemDB
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.PixabayAPI
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDB::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database : ShoppingItemDB
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi() : PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)

    }
}