package ar.com.wolox.android.example.ui.login.repository;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.ui.login.model.User;
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import retrofit2.Call;

import static ar.com.wolox.android.example.utils.Extras.UserLogin.USER_EMAIL;
import static ar.com.wolox.android.example.utils.Extras.UserLogin.USER_SESSION;

/**
 * Login repository
 */
@ApplicationScope
public class LoginRepository implements LoginRepositoryApi {

    private SharedPreferencesManager mSharedPreferencesManager;
    private RetrofitServices mRetrofitServices;

    @Inject
    LoginRepository(SharedPreferencesManager sharedPreferencesManager, RetrofitServices retrofitServices) {
        this.mSharedPreferencesManager = sharedPreferencesManager;
        this.mRetrofitServices = retrofitServices;
    }

    public void saveLocalLoginData(String userEmail) {
        mSharedPreferencesManager.store(USER_EMAIL, userEmail);
    }

    public String getLocalUserEmail() {
        return mSharedPreferencesManager.get(USER_EMAIL, "");
    }

    public void saveUserSession(User user) {
        mSharedPreferencesManager.store(USER_SESSION, new Gson().toJson(user));
    }

    public User getUserSession() {
        String userJson = mSharedPreferencesManager.get(USER_SESSION, "");
        return new Gson().fromJson(userJson, User.class);
    }

    @Override
    public Call<List<User>> doGetUser(String userEmail, String userPassword) {
        return mRetrofitServices.getService(LoginRepositoryApi.class).doGetUser(userEmail, userPassword);
    }
}