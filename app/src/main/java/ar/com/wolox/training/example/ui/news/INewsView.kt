package ar.com.wolox.training.example.ui.news

import ar.com.wolox.training.example.model.News

interface INewsView {

    fun loadNews(newsList: List<News>?)

    fun showError()

    fun loadMoreNews(newsList: List<News>?)
}