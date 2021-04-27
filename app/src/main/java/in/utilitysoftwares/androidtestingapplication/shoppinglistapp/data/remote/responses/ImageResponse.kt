package `in`.utilitysoftwares.androidtestingapplication.shoppinglistapp.data.remote.responses

data class ImageResponse(
    val hints : List<ImageResult>,
    val total : Int,
    val totalHits : Int
)
