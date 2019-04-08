package ar.com.wolox.training.example.ui.newsdetail

import ar.com.wolox.training.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsDetailActivity : WolmoActivity() {
    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, NewsDetailFragment())
    }

    override fun layout(): Int {
        return R.layout.activity_news_detail
    }
}
