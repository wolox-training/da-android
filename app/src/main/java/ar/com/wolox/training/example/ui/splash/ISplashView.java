package ar.com.wolox.training.example.ui.splash;

import ar.com.wolox.training.example.ui.login.model.User;

/**
 * Splash
 */
interface ISplashView {

    void onUserSessionValidation(User user);
}