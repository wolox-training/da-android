package ar.com.wolox.training.example.ui.splash;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashModule {

    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();
}