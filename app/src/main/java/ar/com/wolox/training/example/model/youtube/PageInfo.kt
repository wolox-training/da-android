package ar.com.wolox.training.example.model.youtube

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PageInfo {
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int? = null
    @SerializedName("resultsPerPage")
    @Expose
    val resultsPerPage: Int? = null
}