package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Snippet {
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("thumbnails")
    @Expose
    val thumbnails: Thumbnails? = null
}