package ru.serget.mybiglist.presenter

import ru.serget.mybiglist.view.IMainActivity

interface IMainPresenter<V: IMainActivity> {
    fun attachView(view: V)
    fun detachView(view: V)
}