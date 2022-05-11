package ru.serget.mybiglist

import android.app.Application
import ru.serget.mybiglist.di.AppComponent
import ru.serget.mybiglist.di.DaggerAppComponent


class App: Application() {

    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
    }
}