package ar.com.wolox.android.example.ui.splash;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * Splash
 */
public class SplashActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.vActivityBaseContent, new SplashFragment());
    }
}