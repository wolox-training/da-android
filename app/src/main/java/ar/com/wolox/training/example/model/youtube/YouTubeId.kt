package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class YouTubeId {
    @SerializedName("kind")
    @Expose
    val kind: String? = null
    @SerializedName("videoId")
    @Expose
    val videoId: String? = null
}