package ru.serget.mybiglist.view.list

interface IAwardingView: IItemView {
    fun setId(id: String)
    fun setCoinPrice(coinPrice: Int)
    fun loadIcon(url: String)
}