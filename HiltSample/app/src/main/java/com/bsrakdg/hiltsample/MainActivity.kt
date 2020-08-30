package com.bsrakdg.hiltsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint // be able to have dependencies inject in
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
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
    private val someString: String,
    private val gson: Gson
) {
    fun doAThing(): String {
        return "A thins, $someString"
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

class SomeInterfaceImpl
@Inject
constructor(
    private val someString: String
) : SomeInterface {
    override fun getAThing(): String {
        return "a thing! : $someString"
    }
}

interface SomeInterface {
    fun getAThing(): String
}


@InstallIn(ApplicationComponent::class) // You can change Activity, Fragment, vs component
@Module
class MyModule {
    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "some string"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
        someString: String
    ): SomeInterface {
        return SomeInterfaceImpl(someString)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}