package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class YouTubeSearchResponse {
    @SerializedName("pageInfo")
    @Expose
    val pageInfo: PageInfo? = null
    @SerializedName("items")
    @Expose
    val items: List<YouTubeSearchItem>? = null
}