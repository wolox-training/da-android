package ar.com.wolox.training.example.di

import android.app.Application
import ar.com.wolox.training.example.BootstrapApplication
import ar.com.wolox.training.example.di.module.MiscModule
import ar.com.wolox.training.example.ui.home.HomeModule
import ar.com.wolox.training.example.ui.login.LoginModule
import ar.com.wolox.training.example.ui.newsdetail.NewsDetailModule
import ar.com.wolox.training.example.ui.signup.SignUpModule
import ar.com.wolox.training.example.ui.splash.SplashModule
import ar.com.wolox.training.example.ui.viewpager.ViewPagerActivityModule
import ar.com.wolox.wolmo.core.di.modules.ContextModule
import ar.com.wolox.wolmo.core.di.modules.DefaultModule
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(dependencies = [NetworkingComponent::class],
        modules = [AndroidSupportInjectionModule::class, DefaultModule::class, ContextModule::class,
            AppModule::class, LoginModule::class, HomeModule::class, SignUpModule::class, SplashModule::class, NewsDetailModule::class, ViewPagerActivityModule::class, MiscModule::class])
interface AppComponent : AndroidInjector<BootstrapApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BootstrapApplication>() {
        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun sharedPreferencesName(sharedPrefName: String): Builder

        abstract fun networkingComponent(networkingComponent: NetworkingComponent): Builder
    }
}