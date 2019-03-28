package ar.com.wolox.android.example.ui.signup;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SignUpModule {

    @ContributesAndroidInjector
    abstract SignUpActivity signUpActivity();

    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();
}
