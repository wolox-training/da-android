package ar.com.wolox.training.example.ui.home

import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import ar.com.wolox.training.R
import ar.com.wolox.training.example.ui.news.NewsFragment
import ar.com.wolox.training.example.ui.profile.ProfileFragment
import ar.com.wolox.training.example.ui.youtubesearch.YouTubeSearchFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    // Fragments for Tabs
    @Inject
    internal lateinit var newsFragment: NewsFragment
    @Inject
    internal lateinit var profileFragment: ProfileFragment
    @Inject
    internal lateinit var youtubeSearchFragment: YouTubeSearchFragment

    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun init() {
        setupTabs()
    }

    override fun layout(): Int {
        return R.layout.fragment_home
    }

    private fun setupTabs() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(newsFragment, getString(R.string.tab_news)),
                Pair<Fragment, String>(youtubeSearchFragment, getString(R.string.tab_search)),
                Pair<Fragment, String>(profileFragment, getString(R.string.tab_profile)))
        vViewPager.adapter = fragmentPagerAdapter
        tabs?.setupWithViewPager(vViewPager)
        setupIcons()
    }

    private fun setupIcons() {
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_news_list_off)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_action_youtube)
        tabs.getTabAt(2)?.setIcon(R.drawable.ic_profile_off)
    }
}