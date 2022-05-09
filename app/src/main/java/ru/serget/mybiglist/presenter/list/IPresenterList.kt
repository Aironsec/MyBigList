package ru.serget.mybiglist.presenter.list

import ru.serget.mybiglist.view.list.IItemView

interface IPresenterList<V: IItemView> {
    fun getCount(): Int
    fun bindView(view: V)
}