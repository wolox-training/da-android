package ar.com.wolox.training.example.ui.signup;

import ar.com.wolox.training.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * SignUp form
 */
public class SignUpActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.vActivityBaseContent, new SignUpFragment());
    }
}
