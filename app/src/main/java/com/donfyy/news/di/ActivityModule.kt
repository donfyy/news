package com.donfyy.news.di

import com.donfyy.news.MainActivity
import com.donfyy.news_module.di.MainActivityScope
import com.donfyy.news_module.di.NewsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @MainActivityScope
    @ContributesAndroidInjector(modules = [NewsFragmentModule::class])
    abstract fun mainActivityInjector(): MainActivity
}