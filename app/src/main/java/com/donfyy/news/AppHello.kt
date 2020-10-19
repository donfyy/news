package com.donfyy.news

import com.blankj.utilcode.util.LogUtils
import com.donfyy.news_module.di.AppScope
import javax.inject.Inject

@AppScope
class AppHello @Inject constructor() {
    fun sayHello() {
        LogUtils.d("hello!$this")
    }
}