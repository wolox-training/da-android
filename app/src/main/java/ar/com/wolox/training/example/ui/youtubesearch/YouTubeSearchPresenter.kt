package ar.com.wolox.training.example.ui.youtubesearch

import android.util.Log
import ar.com.wolox.training.BuildConfig
import ar.com.wolox.training.example.model.youtube.YouTubeSearchResponse
import ar.com.wolox.training.example.network.YouTubeSearchService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class YouTubeSearchPresenter @Inject constructor() : BasePresenter<IYouTubeSearchView>() {

    private val youTubeSearchService by lazy { YouTubeSearchPresenter.create() }

    fun getSearch(query: String) {
        youTubeSearchService.getNews(BuildConfig.API_KEY, query).enqueue(object : Callback<YouTubeSearchResponse> {
            override fun onFailure(call: Call<YouTubeSearchResponse>, t: Throwable) {
                Log.v("TAG", t.message)
            }

            override fun onResponse(call: Call<YouTubeSearchResponse>, response: Response<YouTubeSearchResponse>) {
                view.onYouTubeSearchResponse(response.body()?.items)
            }
        })
    }

    companion object {
        fun create(): YouTubeSearchService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.YOUTUBE_BASE_URL)
                    .build()

            return retrofit.create(YouTubeSearchService::class.java)
        }
    }
}