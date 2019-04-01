package ar.com.wolox.android.example.ui.home

import android.support.v4.app.Fragment
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.news.NewsFragment
import ar.com.wolox.android.example.ui.profile.ProfileFragment
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : WolmoFragment<HomePresenter>(), IHomeView, HomePagerAdapter.HomePagesInit {

    // Fragments for Tabs
    private var newsFragment: Fragment? = null
    private var profileFragment: Fragment? = null

    private var mHomePagerAdapter: HomePagerAdapter? = null

    override fun init() {
        setupTabs()
    }

    override fun layout(): Int {
        return R.layout.fragment_home
    }

    private fun setupTabs() {
        mHomePagerAdapter = HomePagerAdapter(activity, childFragmentManager, this)
        container.adapter = mHomePagerAdapter
        tabs?.setupWithViewPager(container)
        setupIcons()
    }

    private fun setupIcons() {
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_news_list_off)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_profile_off)
    }

    override fun initNewsFragment(position: Int): Fragment? {
        if (newsFragment == null)
            newsFragment = NewsFragment()

        return newsFragment
    }

    override fun initProfileFragment(position: Int): Fragment? {
        if (profileFragment == null)
            profileFragment = ProfileFragment()

        return profileFragment
    }
}