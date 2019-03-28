package ar.com.wolox.android.example.ui.login;

import android.util.Patterns;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * Login Presenter
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginRepository mLoginRepository;

    @Inject
    public LoginPresenter(LoginRepository loginRepository) {
        this.mLoginRepository = loginRepository;
    }

    void saveLocalLoginData(String userEmail) {
        mLoginRepository.saveLoginData(userEmail);
    }

    void getLocalLoginData() {
        getUserEmail();
    }

    private void getUserEmail() {
        getView().onGetUserEmail(mLoginRepository.getUserEmail());
    }

    void validateForm(String userEmail, String userPassword) {
        if (validateUserEmail(userEmail) && validateUserPassword(userPassword)) {
            getView().onFormIsValid();
        }
    }

    private Boolean validateUserEmail(String userEmail) {
        if (isEmptyUserEmail(userEmail)) {
            getView().onEmptyEmail();
            return false;
        } else if (!isValidUserEmail(userEmail)) {
            getView().onInvalidEmail();
            return false;
        } else {
            return true;
        }
    }

    private Boolean validateUserPassword(String userPassword) {
        if (isEmptyUserPassword(userPassword)) {
            getView().onEmptyPassword();
            return false;
        } else {
            return true;
        }
    }

    private Boolean isValidUserEmail(String userEmail) {
        return Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
    }

    private Boolean isEmptyUserEmail(String userEmail) {
        return userEmail.isEmpty();
    }

    private Boolean isEmptyUserPassword(String userPassword) {
        return userPassword.isEmpty();
    }
}