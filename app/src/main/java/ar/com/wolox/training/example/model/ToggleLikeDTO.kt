package ar.com.wolox.training.example.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ToggleLikeDTO(
    @SerializedName("likes")
    @Expose
    val likes: List<Int>? = null
)