package ar.com.wolox.android.example.ui.login;

import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.android.example.ui.login.model.User;
import ar.com.wolox.android.example.ui.login.repository.LoginRepository;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;

/**
 * Login Presenter
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginRepository mLoginRepository;

    @Inject
    public LoginPresenter(LoginRepository loginRepository) {
        this.mLoginRepository = loginRepository;
    }

    void performLogin(String userEmail, String userPassword) {
        getView().onShowProgressBar();
        mLoginRepository.saveLocalLoginData(userEmail);
        mLoginRepository.doGetUser(userEmail, userPassword).enqueue(new NetworkCallback<List<User>>() {
            @Override
            public void onResponseSuccessful(@Nullable List<User> users) {
                validateResponseSuccessFul(users);
                getView().onHideProgressBar();
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int i) {
                getView().onUserLoginFailed();
                getView().onHideProgressBar();
            }

            @Override
            public void onCallFailure(@NotNull Throwable throwable) {
                getView().onUserLoginCallFailed();
                getView().onHideProgressBar();
            }
        });
    }

    private void validateResponseSuccessFul(List<User> users) {
        if (users != null && !users.isEmpty()) {
            getView().onUserLoginSucceeded(users.get(0));
        } else {
            getView().onUserLoginFailed();
        }
    }

    void saveUserSession(User user) {
        mLoginRepository.saveUserSession(user);
    }

    void getLocalLoginData() {
        getUserEmail();
    }

    private void getUserEmail() {
        getView().onGetUserEmail(mLoginRepository.getLocalUserEmail());
    }

    public void validateForm(String userEmail, String userPassword) {
        if (validateUserEmail(userEmail) && validateUserPassword(userPassword)) {
            getView().onFormIsValid();
        }
    }

    public Boolean validateUserEmail(String userEmail) {
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
        Pattern eMailPattern = Pattern.
                compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
                );

        return eMailPattern.matcher(userEmail).matches();
    }

    private Boolean isEmptyUserEmail(String userEmail) {
        return userEmail.isEmpty();
    }

    private Boolean isEmptyUserPassword(String userPassword) {
        return userPassword.isEmpty();
    }
}