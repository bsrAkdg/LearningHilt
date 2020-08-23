package com.bsrakdg.hiltsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*
    Hilt can provide dependencies to other Android classes that have the @AndroidEntryPoint annotation:
    Application (by using @HiltAndroidApp)
    Activity
    Fragment
    View
    Service
    BroadcastReceiver
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
        println(someClass.doOtherThing())
    }
}


class SomeClass
@Inject
constructor(
    private val someOtherClass: SomeOtherClass // constructor injection
) {
    fun doAThing(): String {
        return "Look I did a thing!"
    }

    fun doOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}

class SomeOtherClass
@Inject
constructor() {
    fun doSomeOtherThing(): String {
        return "Look I did some other thing!"
    }
}