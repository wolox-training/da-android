package ar.com.wolox.android.example.ui.news

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.visibility
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView, NewsAdapter.NewsAdapterBridge {

    var mNewsAdapter: NewsAdapter? = null

    override fun init() {
        handleSwipeToRefresh()
    }

    override fun layout(): Int {
        return R.layout.fragment_news
    }

    override fun loadNews(newsList: List<News>?) {
        bindAdapter(newsList)
    }

    override fun showError() {
        hideProgressBar()
        showMessage(getString(R.string.no_internet_conection_message))
    }

    override fun loadMoreNews(newsList: List<News>?) {
        hideProgressBar()
        mNewsAdapter?.addItems(newsList)
    }

    private fun bindAdapter(newsList: List<News>?) {
        newsList?.let {
            vRvNews?.visibility(true)
            vRvNews?.layoutManager = LinearLayoutManager(activity)
            vRvNews?.addItemDecoration(DividerItemDecoration(vRvNews.context, DividerItemDecoration.VERTICAL))
            mNewsAdapter = NewsAdapter(newsList, this)
            vRvNews?.adapter = mNewsAdapter

            hideProgressBar()
        }
    }

    private fun handleSwipeToRefresh() {
        swipeToRefresh?.setOnRefreshListener {
            vRvNews?.visibility(false)
            vNewsProgressBar.visibility(true)
            presenter.getNews()
        }
    }

    override fun loadMore() {
        vNewsProgressBar.visibility(true)
        presenter.loadMoreNews(0)
    }

    private fun hideProgressBar() {
        vNewsProgressBar.visibility(false)

        // Hide progress bar from SwipeToRefresh
        swipeToRefresh?.isRefreshing = false
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}