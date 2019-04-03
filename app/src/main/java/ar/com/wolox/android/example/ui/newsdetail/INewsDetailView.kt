package ar.com.wolox.android.example.ui.newsdetail

interface INewsDetailView {
    fun showError()

    fun onToggleSuccess(like: Boolean)
}