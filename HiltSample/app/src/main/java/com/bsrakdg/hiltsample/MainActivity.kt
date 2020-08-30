package com.bsrakdg.hiltsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint // be able to have dependencies inject in
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing1())
        println(someClass.doAThing2())

    }
}

@AndroidEntryPoint // be able to have dependencies inject in
class MyFragment : Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}

class SomeClass
@Inject
constructor(
    // private val someDependency: SomeDependency
    @Impl1 private val someInterfaceImpl1: SomeInterface,
    @Impl2 private val someInterfaceImpl2: SomeInterface
) {

    fun doAThing1(): String {
        return "A things, ${someInterfaceImpl1.getAThing()}"
    }

    fun doAThing2(): String {
        return "A things, ${someInterfaceImpl2.getAThing()}"
    }
}

class SomeDependency
@Inject
constructor(
) {
    fun getAThing(): String {
        return "a thing!"
    }

}

class SomeInterfaceImpl1
@Inject
constructor(
) : SomeInterface {
    override fun getAThing(): String {
        return "A Thing1"
    }
}

class SomeInterfaceImpl2
@Inject
constructor(
) : SomeInterface {
    override fun getAThing(): String {
        return "A Thing2"
    }
}

interface SomeInterface {
    fun getAThing(): String
}


@InstallIn(ApplicationComponent::class) // You can change Activity, Fragment, vs component
@Module
class MyModule {

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1(): SomeInterface {
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(): SomeInterface {
        return SomeInterfaceImpl2()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2