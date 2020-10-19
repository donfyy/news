package com.donfyy.news.di

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider


@Component(modules = [Test1Module::class], dependencies = [Test2Component::class])
interface Test1Component {
    fun inject(t: Test1)

//    @Component.Factory
//    interface Factory {
//        fun build(m: Test1Module, c: Test2Component): Test1Component
//    }
}

class Test1 {
    @Inject
    lateinit var foo: Test1Foo
    @Inject
    lateinit var foo2: Test2Foo
    @Inject
    lateinit var foo2Provider: Provider<Test2Foo>
}

fun test() {
    val t1 = Test1()
    val test2Component = DaggerTest2Component.builder().test2Module(Test2Module()).build()
    DaggerTest1Component.builder()
        .test1Module(Test1Module())
        .test2Component(test2Component)
        .build().inject(t1);
    // ...
}

@Component(modules = [Test2Module::class])
interface Test2Component {
    fun getTest2Foo(): Test2Foo
    fun getTest2FooProvider(): Provider<Test2Foo>
}

@Module
class Test1Module {
    @Provides
    fun provideFoo() = Test1Foo()
}

class Test1Foo

@Module
class Test2Module {
    @Provides
    fun provideFoo() = Test2Foo()
}

class Test2Foo