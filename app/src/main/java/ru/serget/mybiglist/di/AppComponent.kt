package ru.serget.mybiglist.di

import dagger.Component
import ru.serget.mybiglist.MainActivity
import ru.serget.mybiglist.di.module.ApiModule
import ru.serget.mybiglist.di.module.LoadImageModule
import ru.serget.mybiglist.di.module.RepoModule
import ru.serget.mybiglist.di.module.ThreadModule
import ru.serget.mybiglist.presenter.MainPresenter
import ru.serget.mybiglist.view.IMainActivity
import javax.inject.Singleton


@Component(
    modules = [
        ApiModule::class,
        LoadImageModule::class,
        ThreadModule::class,
        RepoModule::class
    ]
)

@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter<IMainActivity>)
}