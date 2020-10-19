package com.donfyy.news_module.di

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject

@MainActivityScope
class ActivityHello @Inject constructor() {
    fun sayHello() {
        LogUtils.d("hello!$this")
    }
}