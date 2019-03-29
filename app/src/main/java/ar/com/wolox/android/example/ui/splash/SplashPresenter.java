package ar.com.wolox.android.example.ui.splash;

import javax.inject.Inject;

import ar.com.wolox.android.example.ui.login.model.User;
import ar.com.wolox.android.example.ui.login.repository.LoginRepository;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * Splash presenter
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private LoginRepository mLoginRepository;

    @Inject
    public SplashPresenter(LoginRepository loginRepository) {
        this.mLoginRepository = loginRepository;
    }

    void validateUserSession() {
        User userSession = mLoginRepository.getUserSession();
        getView().onUserSessionValidation(userSession);
    }
}