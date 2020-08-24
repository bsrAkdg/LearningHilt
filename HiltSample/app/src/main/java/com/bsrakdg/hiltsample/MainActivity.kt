package com.bsrakdg.hiltsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
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

@ActivityScoped // as long as activity alive
class SomeClass
@Inject
constructor(
) {
    fun doAThing(): String {
        return "Look I did a thing!"
    }

}

