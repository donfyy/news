package com.donfyy.news

import com.donfyy.news.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {
    @Inject
    lateinit var hello: AppHello
//may not reference bindings with different scopes
//    @Inject
//    lateinit var activityHello: ActivityHello
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        hello.sayHello()
    }
}