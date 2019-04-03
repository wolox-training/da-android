package ar.com.wolox.android.example.ui.newsdetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsDetailModule {

    @ContributesAndroidInjector
    internal abstract fun newsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun newsDetailFragment(): NewsDetailFragment
}