package ar.com.wolox.training.example.ui.youtubesearch

import ar.com.wolox.training.example.model.youtube.YouTubeSearchItem

interface IYouTubeSearchView {
    fun onYouTubeSearchResponse(items: List<YouTubeSearchItem>?)
}