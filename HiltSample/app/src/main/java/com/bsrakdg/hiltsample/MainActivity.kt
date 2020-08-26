package com.bsrakdg.hiltsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
    private val someInterfaceImpl: SomeInterfaceImpl // for testing
) {
    fun doAThing(): String {
        // return "Look ${someDependency.getAThing()}"
        return "Look ${someInterfaceImpl.getAThing()}"
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
) : SomeInterface {
    override fun getAThing(): String {
        return "a thing!"
    }
}

interface SomeInterface {
    fun getAThing(): String
}