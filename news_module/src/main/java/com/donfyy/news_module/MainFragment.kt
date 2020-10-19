package com.donfyy.news_module

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.donfyy.common_module.base.BaseFragment
import com.donfyy.news_module.di.ActivityHello
import javax.inject.Inject

@Route(path = "/news/MainFragment")
class MainFragment : BaseFragment() {
    @Inject
    lateinit var activityHello: ActivityHello
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHello.sayHello()
    }
}