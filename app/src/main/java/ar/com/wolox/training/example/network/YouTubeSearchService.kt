package ar.com.wolox.training.example.network

import ar.com.wolox.training.example.model.youtube.YouTubeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeSearchService {

    @GET("search?part=snippet&type=video")
    fun getNews(@Query("key") apiKey: String, @Query("q") query: String): Call<YouTubeSearchResponse>
}