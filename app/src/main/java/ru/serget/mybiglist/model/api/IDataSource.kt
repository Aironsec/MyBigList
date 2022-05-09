package ru.serget.mybiglist.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.serget.mybiglist.model.entity.Root

interface IDataSource {
    @GET("r/aww/hot.json")
    fun getAllAwarding() : Single<Root>
}