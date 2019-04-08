package ar.com.wolox.training.example.ui.youtubesearch

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import ar.com.wolox.training.R
import ar.com.wolox.training.example.model.youtube.YouTubeSearchItem
import ar.com.wolox.training.example.utils.visibility
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_you_tube_search.*
import javax.inject.Inject

class YouTubeSearchFragment @Inject constructor() : WolmoFragment<YouTubeSearchPresenter>(), IYouTubeSearchView, YouTubeSearchAdapter.SearchYouTubeAdapterBridge {

    private var mYouTubeSearchAdapter: YouTubeSearchAdapter? = null

    override fun init() {
    }

    override fun layout(): Int {
        return R.layout.fragment_you_tube_search
    }

    override fun onYouTubeSearchResponse(items: List<YouTubeSearchItem>?) {
        bindAdapter(items)
    }

    override fun setListeners() {
        vYouTubeSearchButton?.setOnClickListener { presenter.getSearch(vYouTubeSearchInput.text.toString()) }
    }

    private fun bindAdapter(searchList: List<YouTubeSearchItem>?) {
        searchList?.let {
            rvFragmentYouTubeSearch?.visibility(true)
            rvFragmentYouTubeSearch?.layoutManager = LinearLayoutManager(activity)
            rvFragmentYouTubeSearch?.addItemDecoration(DividerItemDecoration(rvFragmentYouTubeSearch.context, DividerItemDecoration.VERTICAL))
            mYouTubeSearchAdapter = YouTubeSearchAdapter(searchList, this)
            rvFragmentYouTubeSearch?.adapter = mYouTubeSearchAdapter
        }
    }

    override fun onItemClick(item: YouTubeSearchItem) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + item.id?.videoId)))
    }
}
