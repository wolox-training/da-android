package ar.com.wolox.training.example.ui.profile

import ar.com.wolox.training.example.ui.login.repository.LoginRepository
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(private val mLoginRepository: LoginRepository) : BasePresenter<IProfileView>() {

    fun deleteUserSession() {
        mLoginRepository.deleteUserSession()
        view.onUserSessionDeleted()
    }

    fun loadUserData() {
        mLoginRepository.userSession?.let {
            view.onLoadUserData(it)
        }
    }
}