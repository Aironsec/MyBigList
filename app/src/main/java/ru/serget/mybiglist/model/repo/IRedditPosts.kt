package ru.serget.mybiglist.model.repo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.serget.mybiglist.model.entity.AllAwarding

interface IRedditPosts {
    fun getBigList(): Single<List<AllAwarding>>
}