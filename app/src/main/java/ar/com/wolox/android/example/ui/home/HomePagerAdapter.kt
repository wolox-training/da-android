package ar.com.wolox.android.example.ui.home

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ar.com.wolox.android.R

class HomePagerAdapter(private val context: Context?, fm: FragmentManager?, private val homePagesInit: HomePagesInit) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> homePagesInit.initNewsFragment(p0)
            1 -> homePagesInit.initProfileFragment(p0)
            else -> homePagesInit.initNewsFragment(p0)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.getString(R.string.tab_news)
            1 -> context?.getString(R.string.tab_profile)
            else -> context?.getString(R.string.tab_news)
        }
    }

    interface HomePagesInit {
        fun initNewsFragment(position: Int): Fragment?
        fun initProfileFragment(position: Int): Fragment?
    }
}