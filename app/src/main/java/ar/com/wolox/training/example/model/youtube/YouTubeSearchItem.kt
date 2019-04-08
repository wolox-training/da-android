package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class YouTubeSearchItem {
    @SerializedName("snippet")
    @Expose
    val snippet: Snippet? = null
    @SerializedName("id")
    @Expose
    val id: YouTubeId? = null
}