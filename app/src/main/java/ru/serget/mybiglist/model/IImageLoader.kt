package ru.serget.mybiglist.model


interface IImageLoader<T> {
    fun loadLeftImage(url:String, container: T)
}