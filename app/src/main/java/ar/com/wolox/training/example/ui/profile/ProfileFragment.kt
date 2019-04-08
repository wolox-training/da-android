package ar.com.wolox.training.example.ui.profile

import android.content.Intent
import ar.com.wolox.training.R
import ar.com.wolox.training.example.ui.login.LoginActivity
import ar.com.wolox.training.example.ui.login.model.User
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>(), IProfileView {

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun init() {
        initGoogleApiClient()
        loadUserData()
    }

    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    override fun setListeners() {
        vProfileLogOutButton?.setOnClickListener { logOut() }
    }

    private fun logOut() {
        logOutGoogleSignIn()
    }

    override fun onUserSessionDeleted() {
        goToLogin()
    }

    override fun onLoadUserData(user: User) {
        bindUserData(user)
    }

    private fun logOutGoogleSignIn() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).addStatusListener {
            if (it.isSuccess) deleteUserSession()
        }
    }

    private fun initGoogleApiClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        activity?.let { ctx ->
            mGoogleApiClient = GoogleApiClient.Builder(ctx)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()
        }

        mGoogleApiClient?.connect()
    }

    private fun deleteUserSession() {
        presenter.deleteUserSession()
    }

    private fun goToLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity?.finish()
    }

    private fun loadUserData() {
        presenter.loadUserData()
    }

    private fun bindUserData(user: User) {
        vProfileUserName?.text = user.name
        vProfileUserEmail?.text = user.email
    }
}