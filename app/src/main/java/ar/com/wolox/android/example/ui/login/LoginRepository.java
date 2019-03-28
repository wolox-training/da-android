package ar.com.wolox.android.example.ui.login;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager;

import static ar.com.wolox.android.example.utils.Extras.UserLogin.USER_EMAIL;

/**
 * Login repository
 */
@ApplicationScope
public class LoginRepository {

    private SharedPreferencesManager mSharedPreferencesManager;

    @Inject
    LoginRepository(SharedPreferencesManager sharedPreferencesManager) {
        this.mSharedPreferencesManager = sharedPreferencesManager;
    }

    void saveLoginData(String userEmail) {
        mSharedPreferencesManager.store(USER_EMAIL, userEmail);
    }

    String getUserEmail() {
        return mSharedPreferencesManager.get(USER_EMAIL, "");
    }
}
