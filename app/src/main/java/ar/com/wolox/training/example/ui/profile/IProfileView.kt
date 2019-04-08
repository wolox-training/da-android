package ar.com.wolox.training.example.ui.profile

import ar.com.wolox.training.example.ui.login.model.User

interface IProfileView {
    fun onUserSessionDeleted()

    fun onLoadUserData(user: User)
}