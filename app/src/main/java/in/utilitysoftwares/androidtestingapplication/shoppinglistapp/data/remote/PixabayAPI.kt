package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote

import `in`.utilitysoftwares.androidtestingapplication.BuildConfig
import `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchString: String,
        @Query("key") apiKey : String
    ): Response<ImageResponse>
}