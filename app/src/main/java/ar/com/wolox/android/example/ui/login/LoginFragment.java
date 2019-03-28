package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.signup.SignUpActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

import static ar.com.wolox.android.example.utils.Constants.WOLOX_WEB;

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

    @BindView(R.id.vSignUpButton)
    Button vSignUpButton;

    @BindView(R.id.vLoginTermsAndConditions)
    TextView vLoginTermsAndConditions;

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
        vLoginTermsAndConditions.setOnClickListener(v -> openTermsAndConditions());
        vSignUpButton.setOnClickListener(v -> openSignUpPage());
    }

    private void attemptToLogin() {
        String userEmail = vUserEmailInput.getText().toString();
        String password = vUserPasswordInput.getText().toString();
        getPresenter().validateForm(userEmail, password);
    }

    private void openTermsAndConditions() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(WOLOX_WEB));
        startActivity(i);
    }

    private void openHomePage() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    private void openSignUpPage() {
        startActivity(new Intent(getActivity(), SignUpActivity.class));
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

    @Override
    public void onLoginDataSaved() {
        openHomePage();
    }

    private void saveLocalLoginData() {
        getPresenter().saveLocalLoginData(vUserEmailInput.getText().toString());
    }

    private void loadLocalLoginData() {
        getPresenter().getLocalLoginData();
    }
}