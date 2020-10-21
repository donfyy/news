package com.donfyy.news.di

import com.donfyy.news_module.di.MainActivityScope
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@MainActivityScope
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

    @Inject
    lateinit var iTest2Service: ITest2Service
}

fun test() {
    val t1 = Test1()
    val test2Component = DaggerTest2Component.builder().build()
    DaggerTest1Component.builder()
        .test1Module(Test1Module())
        .test2Component(test2Component)
        .build().inject(t1)
    // ...
}

@Singleton
@Component(modules = [Test2Module::class])
interface Test2Component {
    fun inject(t: Test2)
    fun getTest2Foo(): Test2Foo
    fun getTest2FooProvider(): Provider<Test2Foo>
    fun getITest2Service(): ITest2Service
}

class Test2 {
    @Inject
    lateinit var t2: ITest2Service

    @Inject
    lateinit var foo: Test2Foo
}

@Module
class Test1Module {
    @Provides
    fun provideFoo() = Test1Foo()
}

class Test1Foo

@Module
abstract class Test2Module {
    companion object {
        @Provides
        fun provideFoo() = Test2Foo()
    }

    @Binds
    abstract fun bindITest2Service(s: Test2Service): ITest2Service
}

class Test2Foo

interface ITest2Service {}

@Singleton
class Test2Service @Inject constructor() : ITest2Service

@Singleton
@Component(modules = [Test3Module::class, Test3Module2::class])
interface Test3Component {
    fun inject(t: Test3)
    fun getFoo(): Test3Foo
    fun getModule2(): Test3Module2
//    fun getTest3Module2():Test3Module2
    @Component.Builder
    interface Builder {
//        @BindsInstance
//        fun foo(f: Test3Foo): Builder
        fun module2(m: Test3Module2): Builder
        fun build(): Test3Component
    }
}

class Test3 {

    @Inject
    lateinit var t2: ITest3Service
//    @Inject
//    lateinit var b: Test3Bar
    // 通过@Inject提供的类，在依赖处不能使用接口，只能使用类名
    // 如果使用接口，需要在Module中提供
//    @Inject lateinit var t2 : Test3Service
}

@Module
abstract class Test3Module {
    // ITest3Service的实例将有Test3Service来提供
    @Binds
    abstract fun provideITest3Service(s: Test3Service): ITest3Service
    companion object {
        @Provides
        fun provideFoo(b:Test3Bar): Test3Foo {
            return Test3Foo(b)
        }
    }
}

@Module
class Test3Module2 {
    @Provides
    fun provideModule2() = this
    @Provides
    fun provideTest3Bar() = Test3Bar()
}

class Test3Foo(b: Test3Bar)
class Test3Bar

interface ITest3Service {}

@Singleton
class Test3Service @Inject constructor() : ITest3Service
