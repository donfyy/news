package com.donfyy.news

import android.os.Bundle
import com.donfyy.common_module.base.BaseActivity
import com.donfyy.news_module.MainFragment
import com.donfyy.news_module.TestFragment
import com.donfyy.news_module.di.ActivityHello
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var hello: AppHello

    @Inject
    lateinit var activityHello: ActivityHello
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.content, MainFragment())
            add(R.id.content, TestFragment())
            commit()
        }
        hello.sayHello()
    }
}