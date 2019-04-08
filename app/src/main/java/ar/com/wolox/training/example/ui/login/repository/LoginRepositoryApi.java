package ar.com.wolox.training.example.ui.login.repository;

import java.util.List;

import ar.com.wolox.training.example.ui.login.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Login RepositoryAapi
 */
public interface LoginRepositoryApi {

    @GET("/users")
    Call<List<User>> doGetUser(@Query("email") String userEmail, @Query("password") String userPassword);
}