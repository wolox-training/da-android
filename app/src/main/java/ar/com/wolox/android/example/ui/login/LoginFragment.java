package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.login.model.User;
import ar.com.wolox.android.example.ui.signup.SignUpActivity;
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

    @BindView(R.id.vSignUpButton)
    Button vSignUpButton;

    @BindView(R.id.vLoginTermsAndConditions)
    TextView vLoginTermsAndConditions;

    @BindView(R.id.vLoginProgressBar)
    ProgressBar vLoginProgressBar;

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        loadLocalLoginData();
        bindTermsAndConditionsLink();
    }

    @Override
    public void setListeners() {
        vLoginButton.setOnClickListener(v -> attemptToLogin());
        vSignUpButton.setOnClickListener(v -> openSignUpPage());
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
        performLogin();
    }

    @Override
    public void onGetUserEmail(String userEmail) {
        vUserEmailInput.setText(userEmail);
    }

    @Override
    public void onUserLoginSucceeded(User user) {
        getPresenter().saveUserSession(user);
        openHomePage();
    }

    @Override
    public void onUserLoginFailed() {
        showMessage(getString(R.string.login_failed_message));
    }

    @Override
    public void onUserLoginCallFailed() {
        showMessage(getString(R.string.no_internet_conection_message));
    }

    @Override
    public void onShowProgressBar() {
        vLoginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgressBar() {
        vLoginProgressBar.setVisibility(View.GONE);
    }

    private void attemptToLogin() {
        String userEmail = vUserEmailInput.getText().toString();
        String password = vUserPasswordInput.getText().toString();
        getPresenter().validateForm(userEmail, password);
    }

    private void openHomePage() {
        if (getActivity() != null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
    }

    private void performLogin() {
        getPresenter().performLogin(vUserEmailInput.getText().toString(), vUserPasswordInput.getText().toString());
    }

    private void loadLocalLoginData() {
        getPresenter().getLocalLoginData();
    }

    private void bindTermsAndConditionsLink() {
        vLoginTermsAndConditions.setText(Html.fromHtml(getString(R.string.fragment_login_terms_and_conditions)));
        vLoginTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}