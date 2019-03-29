package ar.com.wolox.android.example.ui.splash;

import ar.com.wolox.android.example.ui.login.model.User;

/**
 * Splash
 */
interface ISplashView {

    void onUserSessionValidation(User user);
}