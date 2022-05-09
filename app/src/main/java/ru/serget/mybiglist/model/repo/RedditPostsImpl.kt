package ru.serget.mybiglist.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.serget.mybiglist.model.api.IDataSource
import ru.serget.mybiglist.model.entity.AllAwarding
import ru.serget.mybiglist.model.entity.Root

class RedditPostsImpl(private val api: IDataSource) : IRedditPosts {
    private val page = 20L

    override fun getAllAwarding(): Single<List<AllAwarding>> {
        return api.getAllAwarding()
            .subscribeOn(Schedulers.io())
            .map { converterToListAwarding(it) }
    }

    private fun converterToListAwarding(rootData: Root): List<AllAwarding> {
        val allAwarding = mutableListOf<AllAwarding>()
        rootData.data.children.forEach { children->
            children.data.allAwardings.forEach { awarding ->
                allAwarding.add(awarding)
            }
        }
        return allAwarding
    }
}