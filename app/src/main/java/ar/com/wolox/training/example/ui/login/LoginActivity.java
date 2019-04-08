package ar.com.wolox.training.example.ui.login;

import ar.com.wolox.training.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 *  Login activity container
 */
public class LoginActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.vActivityBaseContent, new LoginFragment());
    }
}