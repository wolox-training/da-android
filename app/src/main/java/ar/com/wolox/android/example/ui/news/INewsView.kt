package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.example.model.News

interface INewsView {

    fun loadNews(newsList: List<News>?)

    fun showError()

    fun loadMoreNews(newsList: List<News>?)
}