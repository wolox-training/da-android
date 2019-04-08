package ar.com.wolox.training.example.di

import ar.com.wolox.training.example.ui.example.ExampleActivity
import ar.com.wolox.training.example.ui.example.ExampleFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun exampleActivity(): ExampleActivity

    @ContributesAndroidInjector
    internal abstract fun exampleFragment(): ExampleFragment
}
