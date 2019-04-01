package ar.com.wolox.android.example.ui.home

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class HomeActivity : WolmoActivity() {

    override fun init() {
        setupStatusBarColor()
        replaceFragment(R.id.vActivityBaseContent, HomeFragment())
    }

    override fun layout(): Int {
        return R.layout.activity_home
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupStatusBarColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.setBackgroundDrawableResource(R.drawable.status_bar_gradient)
    }
}