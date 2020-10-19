package com.donfyy.news_module.di

import com.donfyy.news_module.MainFragment
import com.donfyy.news_module.TestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsFragmentModule {

    @ContributesAndroidInjector
    abstract fun mainFragmentInjector(): MainFragment

    @ContributesAndroidInjector
    abstract fun testFragmentInjector(): TestFragment
}