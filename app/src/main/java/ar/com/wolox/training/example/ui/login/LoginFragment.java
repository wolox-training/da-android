package ar.com.wolox.training.example.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import ar.com.wolox.training.R;
import ar.com.wolox.training.example.ui.home.HomeActivity;
import ar.com.wolox.training.example.ui.login.model.User;
import ar.com.wolox.training.example.ui.signup.SignUpActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

import static ar.com.wolox.training.example.utils.Constants.WOLOX_WEB;

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

    @BindView(R.id.vSignInGoogle)
    SignInButton vSignInGoogle;

    private GoogleApiClient mGoogleApiClient;

    private static final Integer GOOGLE_SIGN_IN_RESULT = 1011;

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        loadLocalLoginData();
        initGoogleApiClient();
    }

    @Override
    public void setListeners() {
        vLoginButton.setOnClickListener(v -> attemptToLogin());
        vLoginTermsAndConditions.setOnClickListener(v -> openTermsAndConditions());
        vSignUpButton.setOnClickListener(v -> openSignUpPage());
        vSignInGoogle.setOnClickListener(v -> attemptToGoogleSignIn());
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

    private void openTermsAndConditions() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(WOLOX_WEB));
        startActivity(i);
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

    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN_RESULT) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            getPresenter().handleGoogleSignInResult(result);
        }
    }

    private void initGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        if (getActivity() != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    private void attemptToGoogleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_RESULT);
    }
}