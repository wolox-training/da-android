package ar.com.wolox.android.example.ui.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>(), IProfileView {

    override fun init() {
    }

    override fun layout(): Int {
        return R.layout.fragment_profile
    }
}