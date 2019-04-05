package ar.com.wolox.android.example.ui.splash;

import android.content.Intent;
import android.os.Handler;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.login.LoginActivity;
import ar.com.wolox.android.example.ui.login.model.User;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Splash fragment
 */
public class SplashFragment extends WolmoFragment<SplashPresenter> implements ISplashView {

    private static final Integer SPLASH_TIME = 1000;

    @Override
    public int layout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        Handler handler = new Handler();
        handler.postDelayed(this::validateUserSession, SPLASH_TIME);
    }

    @Override
    public void onUserSessionValidation(User user) {
        if (user == null) {
            openLoginPage();
        } else {
            openHomePage();
        }
    }

    private void openLoginPage() {
        if (getActivity() != null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    private void openHomePage() {
        if (getActivity() != null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
    }

    private void validateUserSession() {
        getPresenter().validateUserSession();
    }
}