package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Thumbnails {
    @SerializedName("default")
    @Expose
    val default: DefaultThumb? = null
}