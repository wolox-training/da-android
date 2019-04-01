package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    override fun init() {
    }

    override fun layout(): Int {
        return R.layout.fragment_news
    }
}