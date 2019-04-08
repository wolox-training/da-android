package ar.com.wolox.training.example.ui.home

import android.app.Activity
import ar.com.wolox.training.example.ui.news.NewsFragment
import ar.com.wolox.training.example.ui.profile.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [(HomeActivitySubcomponent::class)])
abstract class HomeModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivityFactory(
        builder: HomeActivitySubcomponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment
}