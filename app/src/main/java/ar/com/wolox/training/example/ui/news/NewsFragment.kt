package ar.com.wolox.training.example.ui.news

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import ar.com.wolox.training.R
import ar.com.wolox.training.example.model.News
import ar.com.wolox.training.example.ui.newsdetail.NewsDetailActivity
import ar.com.wolox.training.example.ui.newsdetail.NewsDetailFragment.Companion.LIKE_DATA
import ar.com.wolox.training.example.ui.newsdetail.NewsDetailFragment.Companion.NEWS_DATA
import ar.com.wolox.training.example.ui.newsdetail.NewsDetailFragment.Companion.NEWS_DETAIL
import ar.com.wolox.training.example.utils.toast
import ar.com.wolox.training.example.utils.visibility
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView, NewsAdapter.NewsAdapterBridge {

    private var mNewsAdapter: NewsAdapter? = null
    private var mItemOpenedPosition: Int = -1

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
        activity?.toast(getString(R.string.no_internet_conection_message))
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

    override fun onItemClick(news: News, imageView: ImageView, position: Int) {
        openNewsDetail(news, imageView, position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == NEWS_DETAIL) {
            data?.getBooleanExtra(LIKE_DATA, false)?.let {
                mNewsAdapter?.refreshCard(mItemOpenedPosition, it)
            }
        }
    }

    private fun openNewsDetail(news: News, imageView: ImageView, position: Int) {
        activity?.let { ctx ->
            val newsDetailIntent = Intent(ctx, NewsDetailActivity::class.java)
            val imageViewPair = Pair.create<View, String>(imageView, getString(R.string.image_transition_name))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(ctx, imageViewPair)
            mItemOpenedPosition = position
            newsDetailIntent.putExtra(NEWS_DATA, news)
            startActivityForResult(newsDetailIntent, NEWS_DETAIL, options.toBundle())
        }
    }

    private fun hideProgressBar() {
        vNewsProgressBar.visibility(false)

        // Hide progress bar from SwipeToRefresh
        swipeToRefresh?.isRefreshing = false
    }
}