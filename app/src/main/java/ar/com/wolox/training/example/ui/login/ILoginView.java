package ar.com.wolox.training.example.ui.login;

import ar.com.wolox.training.example.ui.login.model.User;

/**
 * Login view
 */
public interface ILoginView {

    void onEmptyEmail();

    void onEmptyPassword();

    void onInvalidEmail();

    void onFormIsValid();

    void onGetUserEmail(String userEmail);

    void onUserLoginSucceeded(User user);

    void onUserLoginFailed();

    void onUserLoginCallFailed();

    void onShowProgressBar();

    void onHideProgressBar();
}