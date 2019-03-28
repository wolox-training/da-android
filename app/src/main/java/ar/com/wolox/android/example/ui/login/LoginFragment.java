package ar.com.wolox.android.example.ui.login;

import android.widget.Button;
import android.widget.EditText;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

/**
 * Login fragment
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.vUserEmailInput)
    EditText vUserEmailInput;

    @BindView(R.id.vUserPasswordInput)
    EditText vUserPasswordInput;

    @BindView(R.id.vLoginButton)
    Button vLoginButton;

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        loadLocalLoginData();
    }

    @Override
    public void setListeners() {
        vLoginButton.setOnClickListener(v -> attemptToLogin());
    }

    private void attemptToLogin() {
        String userEmail = vUserEmailInput.getText().toString();
        String password = vUserPasswordInput.getText().toString();
        getPresenter().validateForm(userEmail, password);
    }

    @Override
    public void onEmptyEmail() {
        vUserEmailInput.setError(getString(R.string.fragment_login_empty_email_error_message));
    }

    @Override
    public void onEmptyPassword() {
        vUserPasswordInput.setError(getString(R.string.fragment_login_empty_password_error_message));
    }

    @Override
    public void onInvalidEmail() {
        vUserEmailInput.setError(getString(R.string.fragment_login_invalid_email_error_message));
    }

    @Override
    public void onFormIsValid() {
        saveLocalLoginData();
    }

    @Override
    public void onGetUserEmail(String userEmail) {
        vUserEmailInput.setText(userEmail);
    }

    private void saveLocalLoginData() {
        getPresenter().saveLocalLoginData(vUserEmailInput.getText().toString());
    }

    private void loadLocalLoginData() {
        getPresenter().getLocalLoginData();
    }
}