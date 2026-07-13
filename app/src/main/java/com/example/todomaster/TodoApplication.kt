package com.example.todomaster

import android.app.Application
import com.example.todomaster.di.AppContainer

class TodoApplication : Application() {
    
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
