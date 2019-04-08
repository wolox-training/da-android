package ar.com.wolox.training.example.ui.splash;

import android.content.Intent;
import android.os.Handler;

import ar.com.wolox.training.R;
import ar.com.wolox.training.example.ui.home.HomeActivity;
import ar.com.wolox.training.example.ui.login.LoginActivity;
import ar.com.wolox.training.example.ui.login.model.User;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import static ar.com.wolox.training.example.utils.Constants.SPLASH_TIME;

/**
 * Splash fragment
 */
public class SplashFragment extends WolmoFragment<SplashPresenter> implements ISplashView {

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