package ar.com.wolox.training.example.ui.newsdetail

interface INewsDetailView {
    fun showError()

    fun onToggleSuccess(like: Boolean)
}