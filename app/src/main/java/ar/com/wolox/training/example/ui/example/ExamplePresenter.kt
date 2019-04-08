package ar.com.wolox.training.example.ui.example

import ar.com.wolox.training.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter

import javax.inject.Inject

class ExamplePresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<IExampleView>() {

    fun storeUsername(text: String) {
        mUserSession.username = text
        view.onUsernameSaved()
    }
}
