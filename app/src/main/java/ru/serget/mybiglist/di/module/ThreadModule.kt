package ru.serget.mybiglist.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class ThreadModule {

    @Provides
    fun  uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}