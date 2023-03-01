package com.mvvm.examplearchitecture

import android.app.Application
import com.mvvm.examplearchitecture.repository.MyRepositoryImp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var user: MyRepositoryImp
    override fun onCreate() {
        super.onCreate()
//        user.addUser("salman", "132")
    }
}