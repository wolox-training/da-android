package ar.com.wolox.android.example.network

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.ToggleLikeDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NewsService {

    @GET("/news")
    fun getNews(): Call<List<News>>

    @PATCH("/news/{id}")
    fun toogleLike(@Body toggleLikeDTO: ToggleLikeDTO, @Path("id") newsId: Int): Call<News>
}