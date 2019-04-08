package ar.com.wolox.training.example.ui.example

import android.content.Intent
import ar.com.wolox.training.R
import ar.com.wolox.training.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.training.example.utils.onClickListener
import ar.com.wolox.training.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : WolmoFragment<ExamplePresenter>(), IExampleView {

    override fun layout(): Int = R.layout.fragment_example

    override fun init() {
        vLoginButton.isEnabled = false
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            presenter.storeUsername(vUsernameInput.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewPagerActivity::class.java)
        startActivity(intent)
    }
}
