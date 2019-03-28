package ar.com.wolox.android.example.ui.login;

/**
 * Login view
 */
public interface ILoginView {

    void onEmptyEmail();

    void onEmptyPassword();

    void onInvalidEmail();

    void onFormIsValid();

    void onGetUserEmail(String userEmail);
}