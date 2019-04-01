package ar.com.wolox.android.example.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News {
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("userId")
    @Expose
    val userId: Int? = null
    @SerializedName("createdAt")
    @Expose
    val createdAt: String? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("picture")
    @Expose
    val picture: String? = null
    @SerializedName("text")
    @Expose
    val text: String? = null
    @SerializedName("likes")
    @Expose
    val likes: List<Int>? = null
}