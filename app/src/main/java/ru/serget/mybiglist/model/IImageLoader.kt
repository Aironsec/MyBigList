package ru.serget.mybiglist.model

interface IImageLoader<in T> {
    fun loadLeftImage(url:String, container: T)
}