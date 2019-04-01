package ar.com.wolox.android.example.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News {
    @SerializedName("id")
    @Expose
    private val id: Int? = null
    @SerializedName("userId")
    @Expose
    private val userId: Int? = null
    @SerializedName("createdAt")
    @Expose
    private val createdAt: String? = null
    @SerializedName("title")
    @Expose
    private val title: String? = null
    @SerializedName("picture")
    @Expose
    private val picture: String? = null
    @SerializedName("text")
    @Expose
    private val text: String? = null
    @SerializedName("likes")
    @Expose
    private val likes: List<Int>? = null
}